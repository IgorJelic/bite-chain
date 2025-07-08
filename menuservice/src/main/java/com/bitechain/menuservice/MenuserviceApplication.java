package com.bitechain.menuservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MenuserviceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MenuserviceApplication.class, args);
  }

}
