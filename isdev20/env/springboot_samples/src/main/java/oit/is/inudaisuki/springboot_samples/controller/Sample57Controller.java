package oit.is.inudaisuki.springboot_samples.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.inudaisuki.springboot_samples.model.Fruit;
import oit.is.inudaisuki.springboot_samples.model.FruitMapper;
import oit.is.inudaisuki.springboot_samples.service.AsyncShopService57;

/**
 * /sample5へのリクエストを扱うクラス authenticateの設定をしていれば， /sample5へのアクセスはすべて認証が必要になる
 * 他のクラスと同じRequestMappingも書ける．ただし，特定のメソッドへのGETリクエストのURLは一意じゃないとだめ．
 */
@Controller
@RequestMapping("/sample5")
public class Sample57Controller {

  @Autowired
  FruitMapper fMapper;

  @Autowired
  AsyncShopService57 shop57;

  /**
   * これまでと同様，フルーツのリストをDBから取得してthymeleafで返す処理
   *
   * @param model
   * @return
   */
  @GetMapping("step7")
  public String sample57(ModelMap model) {
    final ArrayList<Fruit> fruits7 = shop57.syncShowFruitsList();
    model.addAttribute("fruits7", fruits7);
    return "sample57.html";
  }

  /**
   * JavaScriptからEventSourceとして呼び出されるGETリクエスト SseEmitterを返すことで，PUSH型の通信を実現する
   *
   * @return
   */
  @GetMapping("step8")
  public SseEmitter sample58() {
    final SseEmitter sseEmitter = new SseEmitter();
    this.shop57.asyncShowFruitsList(sseEmitter);
    return sseEmitter;
  }

  /**
   * 購入処理． 購入リンクをクリックするとDBから削除し，表示を更新する 同時にブラウザで同じページを表示しているユーザのページの表示もPUSHで更新する
   *
   * @param id
   * @param model
   * @return
   */
  @GetMapping("step9")
  public String sample59(@RequestParam Integer id, ModelMap model) {
    final Fruit fruit9 = this.shop57.syncBuyFruits(id);
    model.addAttribute("fruit9", fruit9);

    final ArrayList<Fruit> fruits7 = shop57.syncShowFruitsList();
    model.addAttribute("fruits7", fruits7);

    return "sample57.html";
  }
}
