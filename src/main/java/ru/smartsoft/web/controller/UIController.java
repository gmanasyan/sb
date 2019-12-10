package ru.smartsoft.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.smartsoft.repository.ItemRepo;

/**
 * Контроллер для работы через UI.
 */
@Controller
@RequestMapping("/")
public class UIController {

  protected final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  ItemRepo itemRepo;

  @GetMapping("/")
  public String home(Model model) {
    log.info("Purchases page");
    return "purchases";
  }

}
