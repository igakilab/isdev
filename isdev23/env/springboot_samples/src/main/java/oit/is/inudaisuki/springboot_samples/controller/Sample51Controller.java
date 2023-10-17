package oit.is.inudaisuki.springboot_samples.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.inudaisuki.springboot_samples.model.Fruit;
import oit.is.inudaisuki.springboot_samples.model.FruitMapper;

/**
 * /sample5へのリクエストを扱うクラス authenticateの設定をしていれば， /sample5へのアクセスはすべて認証が必要になる
 */
@Controller
@RequestMapping("/sample5")
public class Sample51Controller {

  @Autowired
  FruitMapper fMapper;

  @GetMapping("step1")
  public String sample51() {
    return "sample51.html";
  }

  @GetMapping("step2")
  public String sample52(ModelMap model) {
    ArrayList<Fruit> fruits2 = fMapper.selectAllFruit();
    model.addAttribute("fruits2", fruits2);
    return "sample51.html";
  }

  @GetMapping("step3")
  @Transactional
  public String sample53(@RequestParam Integer id, ModelMap model) {
    // 削除対象のフルーツを取得
    Fruit fruit3 = fMapper.selectById(id);
    model.addAttribute("fruit3", fruit3);

    // 削除
    fMapper.deleteById(id);

    // 削除後のフルーツリストを取得
    ArrayList<Fruit> fruits2 = fMapper.selectAllFruit();
    model.addAttribute("fruits2", fruits2);
    return "sample51.html";
  }

  @GetMapping("step4")
  @Transactional
  public String sample54(@RequestParam Integer id, ModelMap model) {
    // 編集対象のフルーツを取得
    Fruit fruit4 = fMapper.selectById(id);
    model.addAttribute("fruit4", fruit4);

    // フルーツリストを取得
    ArrayList<Fruit> fruits2 = fMapper.selectAllFruit();
    model.addAttribute("fruits2", fruits2);
    return "sample51.html";
  }

  /**
   * IDをクエリParamで，果物の名前と値段をフォームで受け取り，DBを更新する
   *
   * @param name
   * @param price
   * @param model
   * @return
   */
  @PostMapping("step5")
  public String sample55(@RequestParam Integer id, @RequestParam String name, @RequestParam Integer price,
      ModelMap model) {
    System.out.println("step5");
    System.out.println(id);
    System.out.println(name);
    System.out.println(price);
    Fruit fruit = new Fruit(id, name, price);
    // update
    fMapper.updateById(fruit);
    // フルーツリストを取得
    ArrayList<Fruit> fruits2 = fMapper.selectAllFruit();
    model.addAttribute("fruits2", fruits2);

    return "sample51.html";
  }

}
