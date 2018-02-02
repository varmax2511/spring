package com.oauth2.client.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration
    extends ResourceServerConfigurerAdapter {
  
  private static final String RESOURCE_ID = "";
  
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
    .antMatcher("/me")
    .authorizeRequests().anyRequest().authenticated();
        
   /* http.
    anonymous().disable()
    .requestMatchers().antMatchers("/user/**", "/me/**")
    .and().authorizeRequests()
    .antMatchers("/user/**", "/me/**").access("hasRole('ADMIN')")
    .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());*/
  }
}