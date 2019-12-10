package ru.smartsoft.web.controller;

import java.util.List;
import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.smartsoft.model.Item;
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

  @RequestMapping(value = "/purchases/{id}",
      method = RequestMethod.GET,
      produces = { MediaType.APPLICATION_XML_VALUE })
  public String getOne(@PathVariable int id, Model model) throws JAXBException {
    log.info("Get one");
    return "itemForm";
  }

  @GetMapping("/add")
  public String addItem(Model model) {
    log.info("Add page");
    List<Item> items = itemRepo.findAll();
    model.addAttribute("items", items);
    log.info("Items {}", items.size());
    return "itemForm";
  }

}
