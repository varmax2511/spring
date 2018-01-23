package com.oauth2.demo.controller;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();

    /*
     * http.authorizeRequests().antMatchers("/resources/*").permitAll()
     * .anyRequest().authenticated().and().formLogin().loginPage("/login")
     * .permitAll().and().logout().permitAll();
     */

    /*
     * http.authorizeRequests().anyRequest().authenticated().and().formLogin()
     * .loginPage("/login").permitAll().and().logout().permitAll();
     */

    http.antMatcher("/**").authorizeRequests()
        .antMatchers("/login", "/webjars/**").permitAll().anyRequest()
        .authenticated().and().logout().logoutSuccessUrl("/").permitAll();
  }
}