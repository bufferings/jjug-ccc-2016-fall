package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableOAuth2Sso
public class SushiApplication extends WebSecurityConfigurerAdapter {

  public static void main(String[] args) {
    SpringApplication.run(SushiApplication.class, args);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.antMatcher("/**").authorizeRequests()
        .antMatchers("/", "/login**", "/user").permitAll().anyRequest()
        .authenticated();
    http.logout().logoutSuccessUrl("/").permitAll();
    http.csrf().disable();
  }

}
