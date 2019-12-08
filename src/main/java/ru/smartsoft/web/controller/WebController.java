package ru.smartsoft.web.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.smartsoft.dto.PurchaseTo;
import ru.smartsoft.model.Item;
import ru.smartsoft.model.Purchase;
import ru.smartsoft.model.User;
import ru.smartsoft.model.converter.JaxbConverter;
import ru.smartsoft.model.converter.sbAdapter;
import ru.smartsoft.repository.ItemRepo;
import ru.smartsoft.repository.PurchaseRepo;
import ru.smartsoft.repository.UserRepo;

@Controller
@RequestMapping("/")
public class WebController {

  protected final Logger log = LoggerFactory.getLogger(getClass());

  private final JaxbConverter jaxbConverter =
      new JaxbConverter("SrvCreatePurchaseRq", "xsd/sb_scheme.xsd");

  @Autowired
  PurchaseRepo purchaseRepo;

  @Autowired
  ItemRepo itemRepo;

  @Autowired
  UserRepo userRepo;

  @GetMapping("/")
  public String home(Model model) {
    log.info("Root page");
    return "home";
  }

  @GetMapping("/purchases")
  public String purchases(Model model) {
    log.info("Purchases");
    List result = purchaseRepo.getAll().stream()
        .map(p -> toDto(p))
        .collect(Collectors.toList());
    model.addAttribute("purchases", result);
    return "purchases";
  }

  @GetMapping("/purchases/{id}")
  public String getOne(@PathVariable int id, Model model) {
    log.info("Get one");
    PurchaseTo result = toDto(purchaseRepo.findById(id).orElse(null));
    model.addAttribute("purchase", result);

    List<Item> items = itemRepo.findAll();
    model.addAttribute("items", items);
    return "itemForm";
  }

  private PurchaseTo toDto(Purchase pr) {
    if (pr != null) {
      PurchaseTo result = new PurchaseTo(
          pr.getId(),
          pr.getDate(),
          pr.getItem().getName(),
          pr.getCount(),
          pr.getAmount(),
          pr.getUser().getName(),
          pr.getUser().getLastName(),
          pr.getUser().getAge());
      return result;
    }
    return null;
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/purchases/{id}",
      headers = {"content-type=application/xml"})
  public String update(@PathVariable int id, @RequestBody String request, Model model) {
    log.info("Update");
    request = java.net.URLDecoder.decode(request, StandardCharsets.UTF_8);
    log.info("Request {}" + request);

    Purchase purchase = sbAdapter.convert(jaxbConverter.getObject(request));
    purchase.setId(id);
    savePurchase(purchase);

    return "purchases";
  }

  @DeleteMapping("/purchases/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void addItem(@PathVariable int id) {
    log.info("Delete");
    purchaseRepo.deleteById(id);
  }

  @GetMapping("/purchases/add")
  public String addItem(Model model) {
    log.info("Add page");
    List<Item> items = itemRepo.findAll();
    model.addAttribute("items", items);
    log.info("Items {}", items.size());
    return "itemForm";
  }

  @RequestMapping(method = RequestMethod.POST, value = "/purchases/",
      headers = {"content-type=application/xml"})
  public String createItem(@RequestBody String request, Model model) {
    log.info("Create");
    request = java.net.URLDecoder.decode(request, StandardCharsets.UTF_8);
    log.info("Request {}" + request);

    Purchase purchase = sbAdapter.convert(jaxbConverter.getObject(request));
    savePurchase(purchase);
    return purchases(model);
  }

  private void savePurchase(Purchase purchase) {
    Item item = itemRepo.findByName(purchase.getItem().getName());
    if (item != null) {
      purchase.setItem(item);
    } else {
      itemRepo.save(purchase.getItem());
    }

    User user = userRepo.findByAllFields(
        purchase.getUser().getName(),
        purchase.getUser().getLastName(),
        purchase.getUser().getAge()
    );
    if (user != null) {
      purchase.setUser(user);
    } else {
      userRepo.save(purchase.getUser());
    }
    purchaseRepo.save(purchase);
  }

  public String error(Model model) {
    log.info("Error page");
    return "error";
  }

}
