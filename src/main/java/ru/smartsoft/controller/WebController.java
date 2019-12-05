package ru.smartsoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WebController {

  @GetMapping("/sb")
  public String greeting(Model model) {
    System.out.println("INFO: sb");
    return "home";
  }
}
