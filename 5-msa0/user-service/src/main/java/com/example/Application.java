package com.example;

import java.security.Principal;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public RequestDumperFilter requestDumperFilter() {
    return new RequestDumperFilter();
  }

  @RestController
  public static class UserController {

    @RequestMapping(path = "/me")
    public ResponseEntity<User> me(Principal principal) {
      if (principal.getName() == null) {
        throw new UsernameNotFoundException("Username not found");
      }
      User user = null;
      user = new User();
      user.setId(1L);
      user.setRole("SEAT");
      user.setUsername("21");
      return new ResponseEntity<User>(user, HttpStatus.OK);
    }
  }

  @Data
  public static class User {
    private Long id;
    private String username;
    private String role;
  }

}
