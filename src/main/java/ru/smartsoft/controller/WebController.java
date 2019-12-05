package ru.smartsoft.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.smartsoft.dto.PurchaseTo;
import ru.smartsoft.model.Purchase;
import ru.smartsoft.repository.PurchaseRepo;

@Controller
@RequestMapping("/")
public class WebController {

  @Autowired
  PurchaseRepo purchaseRepo;

  @GetMapping("/")
  public String greeting(Model model) {
    System.out.println("INFO: sb");
    List result = new ArrayList<PurchaseTo>();
    result = purchaseRepo.getAll().stream()
        .map(p -> toDto(p))
        .collect(Collectors.toList());
    model.addAttribute("purchases", result);
    return "home";
  }

  private PurchaseTo toDto(Purchase pr) {
    PurchaseTo result = new PurchaseTo(
        pr.getId(),
        pr.getDate(),
        pr.getItem().getName(),
        toInt(pr.getItem().getCost()),
        pr.getCount(),
        pr.getUser().getName(),
        pr.getUser().getLastName(),
        pr.getUser().getAge());
    return result;
  }

  private Double toInt(Integer cents) {
    return Double.valueOf(cents)/100;
  }

  @GetMapping("/add")
  public String addItem(Model model) {
    System.out.println("INFO: add");
    return "itemForm";
  }

  @PostMapping("/")
  public String createItem(Model model) {
    System.out.println("INFO: create");
    return greeting(model);
  }

}
