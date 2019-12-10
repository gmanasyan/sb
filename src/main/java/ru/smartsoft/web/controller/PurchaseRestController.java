package ru.smartsoft.web.controller;

import static ru.smartsoft.model.converter.sbAdapter.responsePurchase;
import static ru.smartsoft.model.converter.sbAdapter.responsePurchaseList;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.smartsoft.model.Item;
import ru.smartsoft.model.Purchase;
import ru.smartsoft.model.User;
import ru.smartsoft.model.converter.JaxbConverter;
import ru.smartsoft.model.converter.sbAdapter;
import ru.smartsoft.repository.ItemRepo;
import ru.smartsoft.repository.PurchaseRepo;
import ru.smartsoft.repository.UserRepo;

/**
 * REST контроллер для работы с покупками.
 */
@RestController
@RequestMapping(value = PurchaseRestController.REST_URL)
public class PurchaseRestController {

  static final String REST_URL = "/api/purchases";

  protected final Logger log = LoggerFactory.getLogger(getClass());

  private final JaxbConverter jaxbConverter =
      new JaxbConverter("SrvCreatePurchaseRq", "xsd/sb_scheme.xsd");

  @Autowired
  PurchaseRepo purchaseRepo;

  @Autowired
  ItemRepo itemRepo;

  @Autowired
  UserRepo userRepo;

  @RequestMapping(value = "/", method = RequestMethod.GET,
      produces = { MediaType.APPLICATION_XML_VALUE })
  public String purchases() throws JAXBException {
    log.info("Purchases");
    List<Purchase> purchases = purchaseRepo.getAll();
    return jaxbConverter.getXml(responsePurchaseList(purchases));
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET,
      produces = { MediaType.APPLICATION_XML_VALUE })
  public String getOne(@PathVariable int id) throws JAXBException {
    log.info("Get purchase with id: {}", id);
    Purchase purchase = purchaseRepo.findById(id).orElse(null);
    return jaxbConverter.getXml(responsePurchase(purchase));
  }

  @RequestMapping(value = "/", method = RequestMethod.POST,
      headers = {"content-type=application/xml"})
  @ResponseStatus(value = HttpStatus.CREATED)
  public ResponseEntity<String> createItem(@RequestBody String request) {
    request = java.net.URLDecoder.decode(request, StandardCharsets.UTF_8);
    log.info("Create: {}" + request);
    Purchase purchase = sbAdapter.convert(jaxbConverter.getObject(request));
    Purchase created = savePurchase(purchase);

    URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path(REST_URL + "/{id}")
        .buildAndExpand(created.getId()).toUri();
    return ResponseEntity.created(uriOfNewResource).body("Purchase created");
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
      headers = {"content-type=application/xml"})
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void update(@PathVariable int id, @RequestBody String request) {
    request = java.net.URLDecoder.decode(request, StandardCharsets.UTF_8);
    log.info("Update: {}" + request);
    Purchase purchase = sbAdapter.convert(jaxbConverter.getObject(request));
    purchase.setId(id);
    savePurchase(purchase);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void addItem(@PathVariable int id) {
    log.info("Delete purchase with id {}", id);
    purchaseRepo.deleteById(id);
  }

  /**
   * Метод для создания и апдейта покупки.
   */
  private Purchase savePurchase(Purchase purchase) {
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
    return purchaseRepo.save(purchase);
  }

}
