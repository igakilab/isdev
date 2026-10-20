package oit.is.inudaisuki.springboot_samples.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class AsyncCount58 {

  private int customerCount = 1;// customerロール用カウンター
  private int sellerCount = 1;// sellerロール用カウンター
  private final Logger logger = LoggerFactory.getLogger(AsyncCount58.class);

  /**
   *
   * @param emitter
   * @param role    "CUSTOMER" "SELLER"のいずれかを想定している
   * @throws IOException
   */
  @Async
  public void count(SseEmitter emitter, String role) throws IOException {
    logger.info("AsyncCount58.count");
    try {
      while (true) {
        int counter = 0;
        // CUSTOMERとSELLERでカウンタを分ける
        // この2つ以外のロール場合は常にcounter=0
        if (role.equals("CUSTOMER")) {
          counter = customerCount;
          customerCount++;
        } else if (role.equals("SELLER")) {
          counter = sellerCount;
          sellerCount++;
        }
        // ロールごとのカウンタとロール名を送る
        emitter.send(SseEmitter.event()
            .data(counter)
            .id(role));
        TimeUnit.SECONDS.sleep(1);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
