package com.taskagile.web.pages;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;

@Controller
public class MainController {

     @GetMapping(value = {"/", "/login", "/register"})
     public String entry() {
          return "index";
     }
}
