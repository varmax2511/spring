package com.oauth2.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CompositeFilter;

@Configuration
@EnableOAuth2Client
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  OAuth2ClientContext oauth2ClientContext;

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
        .antMatchers("/", "/login", "/webjars/**").permitAll().anyRequest()
        .authenticated().and().logout().logoutSuccessUrl("/").permitAll().and()
        .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);;
  }

  /**
   * Filter configuration for SSO.
   * 
   * @return
   */
  private Filter ssoFilter() {

    CompositeFilter filter = new CompositeFilter();
    List<Filter> filters = new ArrayList<>();

    filters.add(ssoFilter(google(), googleResource(), "/login/google"));
    filters.add(ssoFilter(github(), githubResource(), "/login/github"));
    filter.setFilters(filters);
    return filter;
    
    /*
     * Github Filter
     */
    /**
    final OAuth2ClientAuthenticationProcessingFilter githubFilter = new OAuth2ClientAuthenticationProcessingFilter(
        "/login/github");
    final OAuth2RestTemplate githubTemplate = new OAuth2RestTemplate(github(),
        oauth2ClientContext);

    githubFilter.setRestTemplate(githubTemplate);
    final UserInfoTokenServices githubTokenServices = new UserInfoTokenServices(
        githubResource().getUserInfoUri(), githubResource().getClientId());
    githubTokenServices.setRestTemplate(githubTemplate);
    githubFilter.setTokenServices(githubTokenServices);

    filters.add(githubFilter);
    */

    /*
     * Google Filter
     */
    /**
    OAuth2ClientAuthenticationProcessingFilter googleFilter = new OAuth2ClientAuthenticationProcessingFilter(
        "/login/google");
    final OAuth2RestTemplate googleTemplate = new OAuth2RestTemplate(google(),
        oauth2ClientContext);
    googleFilter.setRestTemplate(googleTemplate);
    final UserInfoTokenServices googleTokenServices = new UserInfoTokenServices(
        googleResource().getUserInfoUri(), googleResource().getClientId());
    googleTokenServices.setRestTemplate(googleTemplate);
    googleFilter.setTokenServices(googleTokenServices);

    filters.add(googleFilter);

    filter.setFilters(filters);
    return filter;
    */
  }

  private Filter ssoFilter(AuthorizationCodeResourceDetails client, ResourceServerProperties resource, String path) {

    OAuth2ClientAuthenticationProcessingFilter clientFilter = new OAuth2ClientAuthenticationProcessingFilter(
        path);
    final OAuth2RestTemplate clientTemplate = new OAuth2RestTemplate(client,
        oauth2ClientContext);
    clientFilter.setRestTemplate(clientTemplate);
    final UserInfoTokenServices clientTokenServices = new UserInfoTokenServices(
        resource.getUserInfoUri(), resource.getClientId());
    clientTokenServices.setRestTemplate(clientTemplate);
    clientFilter.setTokenServices(clientTokenServices);
    
    return clientFilter;
  }

  @Bean
  @ConfigurationProperties("github.client")
  public AuthorizationCodeResourceDetails github() {
    return new AuthorizationCodeResourceDetails();
  }

  @Bean
  @ConfigurationProperties("github.resource")
  public ResourceServerProperties githubResource() {
    return new ResourceServerProperties();
  }

  @Bean
  @ConfigurationProperties("google.client")
  public AuthorizationCodeResourceDetails google() {
    return new AuthorizationCodeResourceDetails();
  }

  @Bean
  @ConfigurationProperties("google.resource")
  public ResourceServerProperties googleResource() {
    return new ResourceServerProperties();
  }

  @Bean
  public FilterRegistrationBean oauth2ClientFilterRegistration(
      OAuth2ClientContextFilter oauth2ClientContextFilter) {

    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(oauth2ClientContextFilter);
    registration.setOrder(-100);
    return registration;
  }
}