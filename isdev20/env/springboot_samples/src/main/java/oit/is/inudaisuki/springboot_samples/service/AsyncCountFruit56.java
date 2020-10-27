package oit.is.inudaisuki.springboot_samples.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.inudaisuki.springboot_samples.model.Fruit;

@Service
public class AsyncCountFruit56 {
  int count = 1;
  private final Logger logger = LoggerFactory.getLogger(AsyncCountFruit56.class);

  @Async
  public void count(SseEmitter emitter) {
    logger.info("count start");
    while (true) {// 無限ループ
      try {
        logger.info("send:" + count);
        TimeUnit.SECONDS.sleep(1);// 1秒STOP
        emitter.send(count);// ここでsendすると引数をブラウザにpushする
        count++;
      } catch (Exception e) {
        // 例外の名前とメッセージだけ表示する
        logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
        // 例外が発生したらカウントとsendを終了する
        emitter.complete();// emitterの後始末．明示的にブラウザとの接続を一度切る．
        break;
      }
    }

  }

  @Async
  public void pushFruit(SseEmitter emitter) {
    logger.info("pushFruit start");
    Fruit fruit = new Fruit();
    fruit.setName("桃");
    fruit.setPrice(300);
    // 10回sendすると一度接続を終了する．その後ブラウザを開いていればブラウザから自動的に再接続されてまた10回sendされる（以降繰り返し）
    for (int i = 0; i < 10; i++) {
      try {
        logger.info("send(fruit)");
        TimeUnit.SECONDS.sleep(1);// 1秒STOP
        emitter.send(fruit);// fruitのJSONオブジェクトがクライアントに送付される
      } catch (Exception e) {
        // 例外の名前とメッセージだけ表示する
        logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
        // 例外が発生したらカウントとsendを終了する
        break;
      }
    }
    emitter.complete();// emitterの後始末．明示的にブラウザとの接続を一度切る．
  }
}
