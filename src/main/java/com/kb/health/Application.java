package com.kb.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = "com.kb.health")
@ConfigurationPropertiesScan(basePackages = "com.kb.health")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
