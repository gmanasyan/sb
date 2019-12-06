package ru.smartsoft.web;

import java.io.File;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;
import ru.smartsoft.dto.PurchaseRequestTo;
import ru.smartsoft.dto.PurchaseTo;
import ru.smartsoft.model.Item;
import ru.smartsoft.model.Purchase;
import ru.smartsoft.model.User;
import ru.smartsoft.repository.ItemRepo;
import ru.smartsoft.repository.PurchaseRepo;
import ru.smartsoft.repository.UserRepo;

@Controller
@RequestMapping("/")
public class WebController {

  @Autowired
  PurchaseRepo purchaseRepo;

  @Autowired
  ItemRepo itemRepo;

  @Autowired
  UserRepo userRepo;

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
        kopToRub(pr.getItem().getCost()),
        pr.getCount(),
        pr.getUser().getName(),
        pr.getUser().getLastName(),
        pr.getUser().getAge());
    return result;
  }

  private Double kopToRub(Integer kop) {
    return Double.valueOf(kop)/100;
  }

  @GetMapping("/add")
  public String addItem(Model model) {
    System.out.println("INFO: add");
    List<Item> items = itemRepo.findAll();
    model.addAttribute("items", items);
    System.out.println("INFO: " + items.size());
    return "itemForm";
  }

  //@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)

  @RequestMapping(method = RequestMethod.POST, value = "/", headers = {"content-type=application/xml"})
  public String createItem(@RequestBody String request, Model model) {
    System.out.println("INFO: create");
    request = java.net.URLDecoder.decode(request, StandardCharsets.UTF_8);
    System.out.println(request);

    URL fileUrl = getClass().getClassLoader().getResource("xsd/purchase_request.xsd");

    JAXBContext jaxbContext;
    try
    {
      jaxbContext = JAXBContext.newInstance(PurchaseRequestTo.class);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

      //Setup schema validator
      SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema itemSchema = sf.newSchema(new File(fileUrl.getFile()));
      jaxbUnmarshaller.setSchema(itemSchema);

      //Unmarshal xml file
      PurchaseRequestTo purchaseRequestTo = (PurchaseRequestTo) jaxbUnmarshaller
          .unmarshal(new StringReader(request));
      System.out.println(purchaseRequestTo);
      savePurchase(purchaseRequestTo);

    }
    catch (JAXBException | SAXException e)
    {
      System.out.println(e.getCause().getMessage());
      model.addAttribute("errorMessage", e.getCause().getMessage());
      return "error";
    }
    return greeting(model);
  }

  private void savePurchase(PurchaseRequestTo purchaseRequestTo) {
    User activeUser = userRepo.getOne(100001);
    Item item = itemRepo.findByName(purchaseRequestTo.getItemName());
    Purchase purchase = new Purchase(activeUser, item, purchaseRequestTo.getCount(), new Date());
    purchaseRepo.save(purchase);
  }

}
