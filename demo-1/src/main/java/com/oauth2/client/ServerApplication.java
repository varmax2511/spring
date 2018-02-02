package com.oauth2.client;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableAutoConfiguration
@Configuration
@EnableAuthorizationServer
public class ServerApplication extends WebSecurityConfigurerAdapter {

  @RequestMapping({ "/user", "/me" })
  public Map<String, String> user(Principal principal) {
    Map<String, String> map = new LinkedHashMap<>();
    map.put("name", principal.getName());
    return map;
  }

  public static void main(String[] args) {
    new SpringApplicationBuilder(ServerApplication.class)
        .properties("spring.config.name=server").run(args);
  }
  
}
