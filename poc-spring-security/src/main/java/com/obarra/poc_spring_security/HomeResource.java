package com.obarra.poc_spring_security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource
{

  @GetMapping("/")
        public String home() {
        return ("<h1>Welcome</h1><br><a href='/logout'>Logout</a>");
        }


  @GetMapping("/user")
  public String user() {
    return ("<h1>Welcome User</h1><br><a href='/logout'>Logout</a>");
  }

  @GetMapping("/admin")
  public String admin() {
    return ("<h1>Welcome Admin</h1><br><a href='/logout'>Logout</a>");
  }
}
