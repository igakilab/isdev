package oit.is.inudaisuki.springboot_samples.controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.inudaisuki.springboot_samples.service.AsyncCount58;

/**
 * /sample58へのリクエストを扱うクラス
 * Controllerはhtmlを返すコントローラ
 * RestControllerはhtmlではなくjsonを直接返すコントローラ．今回はSseEmitterをjsonで返すメソッドしかないためRestControllerとしている
 */
@RestController
@RequestMapping("/sample58")
public class Sample58Controller {
  // コンソールに出力したいログ情報を簡単に使えるようにするためのクラス
  // どこのクラスでもgetLogger内のクラス名を替えるだけで使える
  // 似たようなロガークラスが大量にあるので import org.slf4j.Logger; を間違えないようにすること
  private final Logger logger = LoggerFactory.getLogger(Sample58Controller.class);

  @Autowired
  private AsyncCount58 counter58;

  // SseEmitterがnewされるたびにカウントアップする数値
  int emitterCounter = 0;

  // 各ロールのemitterを都度newしてmapに保存していって，定期的に異なったメッセージをscheduledで周知できないか
  // 都度newしないと過去の通信が上書きされてしまう
  ConcurrentHashMap<String, SseEmitter> semap = new ConcurrentHashMap<String, SseEmitter>();

  /**
   * @AuthenticationPrincipal ログインユーザの名前やロールを取得するためのアノテーション．Principalと違い，ロールも取得できる．
   * @param user
   * @return
   */
  @GetMapping("step1")
  public SseEmitter pushCount58(@AuthenticationPrincipal UserDetails user) {
    // infoレベルでログを出力する
    logger.info("pushCount");
    logger.info(user.getUsername());
    // ロールをStringでまとめて取得する
    logger.info(user.getAuthorities().toString() + ":toString");

    // ロールを1つずつ取得する場合はこちら
    for (GrantedAuthority g : user.getAuthorities()) {
      logger.info(g.getAuthority());
    }
    // SseEmitterの生成
    SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
    this.emitterCounter++;// newされたemitterの数
    String role = new String();

    // ロール名の一覧に含まれている文字列からロールを判定し，roleに代入
    if (user.getAuthorities().toString().contains("CUSTOMER")) {
      logger.info("CUSTOMER!!");
      role = "CUSTOMER";
    } else if (user.getAuthorities().toString().contains("SELLER")) {
      logger.info("SELLER!!");
      role = "SELLER";
    }

    // mapにemitterCounterを文字列にしたものとemitterを保存する
    String semapId = "" + this.emitterCounter;
    this.semap.put(semapId, emitter);
    try {
      this.counter58.count(emitter, role);
    } catch (IOException e) {
      // 例外の名前とメッセージを表示し，mapから対象のemitterを削除する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
      logger.info("emitter is removed");
      this.semap.remove(semapId);
    }
    return emitter;

  }

  /**
   * 3秒おきにmap内の全SseEmitterにheartbeatメッセージを送るメソッド
   * Scheduledアノテーションは引数をもたないメソッドを一定時間ごとに実行する
   * この例の場合3秒ごと
   * Scheduled を使うときは @SpringBootApplicationアノテーションの前に@EnableSchedulingをつける
   */
  @Scheduled(fixedRate = 3000)
  public void heartbeat() {

    // this.semap内のすべてのIDとSseEmitterを取得し，heartbeatメッセージを送る
    for (Map.Entry<String, SseEmitter> entry : this.semap.entrySet()) {
      logger.info("heartbeat");
      try {
        entry.getValue().send(SseEmitter.event()
            .data("heartbeat")
            .id("" + entry.getKey()));
      } catch (IOException e) {
        // 例外の名前とメッセージを表示し，mapから対象のemitterを削除する
        logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
        logger.info("emitter" + entry.getKey() + " is removed");
        this.semap.remove(entry.getKey());
      }
    }
  }
}
