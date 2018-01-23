package com.oauth2.demo;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextListener;

/**
 * Starter file.
 * 
 * @author varunjai
 *
 */
@SpringBootApplication
@RestController
public class Demo1Application {

  private final Logger logger = LoggerFactory.getLogger(Demo1Application.class);

  @Bean
  public RequestContextListener requestContextListener() {
    return new RequestContextListener();
  }

  /**
   * Get the logged in user.
   * 
   * @param principal
   * @return
   */
  @RequestMapping("/user")
  public Principal user(Principal principal) {
    logger.info("In user method {}");
    return principal;
  }

  public static void main(String[] args) {
    SpringApplication.run(Demo1Application.class, args);
  }
}
