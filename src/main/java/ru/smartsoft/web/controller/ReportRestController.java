package ru.smartsoft.web.controller;

import static ru.smartsoft.model.converter.sbAdapter.responseItem;
import static ru.smartsoft.model.converter.sbAdapter.responsePurchaseList;
import static ru.smartsoft.model.converter.sbAdapter.responseUser;

import java.util.List;
import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.smartsoft.model.Item;
import ru.smartsoft.model.User;
import ru.smartsoft.model.converter.JaxbConverter;
import ru.smartsoft.repository.ItemRepo;
import ru.smartsoft.repository.PurchaseRepo;
import ru.smartsoft.repository.UserRepo;
import ru.smartsoft.util.ApplicationException;

/**
 * REST контроллер для отчетов.
 */
@RestController
@RequestMapping("/api/report")
public class ReportRestController {

  protected final Logger log = LoggerFactory.getLogger(getClass());

  private final JaxbConverter jaxbConverter =
      new JaxbConverter("SrvCreatePurchaseRq", "xsd/sb_scheme.xsd");

  @Autowired
  PurchaseRepo purchaseRepo;

  @Autowired
  ItemRepo itemRepo;

  @Autowired
  UserRepo userRepo;

  @RequestMapping(value = "/week", method = RequestMethod.GET,
      produces = { MediaType.APPLICATION_XML_VALUE })
  public String purchases(Model model) throws JAXBException {
    log.info("Week");
    List purchases = purchaseRepo.getAllLastWeek();
    checkIfNull(purchases, "Нет покупок за последнюю неделю");

    return jaxbConverter.getXml(responsePurchaseList(purchases));
  }

  @RequestMapping(value = "/bestseller/month", method = RequestMethod.GET,
      produces = { MediaType.APPLICATION_XML_VALUE })
  public String bestsellerOfMonth(Model model) throws JAXBException {
    log.info("Bestseller of month");
    Item result = itemRepo.getBestsellerOfMonth();
    checkIfNull(result, "Нет покупок за последний месяц");

    return jaxbConverter.getXml(
        responseItem(result, "Самый покупаемый товар за последний месяц"));
  }

  @RequestMapping(value = "/bestbuyer/halfyear", method = RequestMethod.GET,
      produces = { MediaType.APPLICATION_XML_VALUE })
  public String bestbuyerOfHalfYear(Model model) throws JAXBException {
    log.info("Bestbuyer of halfyear");
    User result = userRepo.getBestsellerOfHalthYear();
    checkIfNull(result, "Нет покупок за последние полгода");

    String description = "Человек совершивший больше всего покупок (по количеству) за полгода";
    return jaxbConverter.getXml(responseUser(result, description));
  }

  @RequestMapping(value = "/bestseller/{age}", method = RequestMethod.GET,
      produces = { MediaType.APPLICATION_XML_VALUE })
  public String bestsellerByAge(@PathVariable("age") Integer age, Model model)
      throws JAXBException {
    log.info("Bestseller by age {}", age);
    Item result = itemRepo.getBestsellerOfAge(age);
    checkIfNull(result, "Нет товара купленного людьми в возрасте " + age);

    String description = "Самый покупаемый товар для людей в возрасте " + age;
    return jaxbConverter.getXml(responseItem(result, description));
  }

  private void checkIfNull(Object object, String msg) {
    if (object == null)  {
      log.info("Ошибка: {}", msg);
      throw new ApplicationException(msg);
    }
  }

}
