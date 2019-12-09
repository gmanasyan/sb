package ru.smartsoft.web.controller;

import static ru.smartsoft.web.CommonUtil.toDto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.smartsoft.model.Item;
import ru.smartsoft.model.User;
import ru.smartsoft.repository.ItemRepo;
import ru.smartsoft.repository.PurchaseRepo;
import ru.smartsoft.repository.UserRepo;

@Controller
@RequestMapping("/report")
public class ReportController {

  protected final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  PurchaseRepo purchaseRepo;

  @Autowired
  ItemRepo itemRepo;

  @Autowired
  UserRepo userRepo;

  @GetMapping("/week")
  public String purchases(Model model) {
    log.info("Week");
    List result = purchaseRepo.getAllLastWeek().stream()
        .map(p -> toDto(p))
        .collect(Collectors.toList());
    model.addAttribute("purchases", result);
    return "purchases";
  }

  @GetMapping("/bestseller/month")
  public String bestsellerOfMonth(Model model) {
    log.info("Bestseller of month");
    Item result = itemRepo.getBestsellerOfMonth();
    model.addAttribute("reportTitle", "Самомый покупаемый товар за последний месяц" );
    model.addAttribute("reportMessage",
        "Название товара: " + result.getName());
    return "report";
  }

  @GetMapping("/bestbuyer/halfyear")
  public String bestbuyerOfHalfYear(Model model) {
    log.info("Bestbuyer of halfyear");
    User result = userRepo.getBestsellerOfHalthYear();
    model.addAttribute("reportTitle", "Человек совершивший больше всего покупок (по стоимости) за полгода" );
    model.addAttribute("reportMessage",
        "Имя и фамилия покупателя: " + result.getName() + " " +result.getLastName());
    return "report";
  }

  @GetMapping("/bestseller/{age}")
  public String bestsellerByAge(@PathVariable("age") Integer age, Model model) {
    log.info("Bestseller by age {}", age);
    Item result = itemRepo.getBestsellerOfAge(age);
    model.addAttribute("reportTitle", "Самый покупаемый товар для людей в возрасте " + age );
    model.addAttribute("reportMessage",
        "Название товара: " + result.getName());
    return "report";
  }

}
