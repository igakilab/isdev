package oit.is.inudaisuki.springboot_samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringbootSamplesApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootSamplesApplication.class, args);
  }

}
