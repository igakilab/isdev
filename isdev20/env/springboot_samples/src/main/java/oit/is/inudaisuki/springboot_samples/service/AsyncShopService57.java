package oit.is.inudaisuki.springboot_samples.service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.inudaisuki.springboot_samples.model.Fruit;
import oit.is.inudaisuki.springboot_samples.model.FruitMapper;

@Service
public class AsyncShopService57 {
  boolean dbUpdated = false;

  private final Logger logger = LoggerFactory.getLogger(AsyncShopService57.class);

  @Autowired
  FruitMapper fMapper;

  public ArrayList<Fruit> syncShowFruitsList() {
    return fMapper.selectAllFruit();
  }

  /**
   * 購入対象の果物IDの果物をDBから削除し，購入対象の果物オブジェクトを返す
   *
   * @param id 購入対象の果物のID
   * @return 購入対象の果物のオブジェクトを返す
   */
  @Transactional
  public Fruit syncBuyFruits(int id) {
    // 削除対象のフルーツを取得
    Fruit fruit = fMapper.selectById(id);

    // 削除
    fMapper.deleteById(id);

    this.dbUpdated = true;

    return fruit;
  }

  /**
   * dbUpdatedがtrueのときのみブラウザにDBからフルーツリストを取得して送付する
   *
   * @param emitter
   */
  @Async
  public void asyncShowFruitsList(SseEmitter emitter) {
    dbUpdated = true;
    try {
      while (true) {
        if (false == dbUpdated) {
          TimeUnit.MILLISECONDS.sleep(500);
          continue;
        }
        ArrayList<Fruit> fruits7 = this.syncShowFruitsList();
        emitter.send(fruits7);
        TimeUnit.MILLISECONDS.sleep(1000);
        dbUpdated = false;
      }
    } catch (Exception e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
    System.out.println("asyncShowFruitsList complete");
  }

}
