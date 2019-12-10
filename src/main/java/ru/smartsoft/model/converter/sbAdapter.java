package ru.smartsoft.model.converter;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.smartsoft.model.Item;
import ru.smartsoft.model.Purchase;
import ru.smartsoft.model.User;
import ru.smartsoft.model.sbxsd.PurchaseInfo;
import ru.smartsoft.model.sbxsd.PurchaseItem;
import ru.smartsoft.model.sbxsd.SrvCreatePurchaseRq;
import ru.smartsoft.model.sbxsd.SrvGetPurchaseItemRs;
import ru.smartsoft.model.sbxsd.SrvGetPurchaseListRs;
import ru.smartsoft.model.sbxsd.SrvGetPurchaseRs;
import ru.smartsoft.model.sbxsd.SrvGetUserRs;
import ru.smartsoft.model.sbxsd.UserInfo;

/**
 * Адаптер транспротных сущьностей.
 */
public class sbAdapter {

  protected static final Logger log = LoggerFactory.getLogger(sbAdapter.class);

  /**
   * Преобразует транспортный объект запроса во внутреннюю сущьность.
   */
  public static Purchase convert(SrvCreatePurchaseRq request) {
    User user = new User();
    user.setName(request.getName());
    user.setLastName(request.getLastName());
    user.setAge(request.getAge());

    Item item = new Item();
    item.setName(request.getPurchaseItem().value());

    Purchase purchase = new Purchase(user, item, request.getCount(), request.getAmount(),
        date(request.getPurchaseDate()));

    return purchase;
  }

  /**
   * Возвращает транспротный объект ответа для товара.
   */
  public static SrvGetPurchaseItemRs responseItem(Item response, String description) {
    SrvGetPurchaseItemRs purchaseItemRs = new SrvGetPurchaseItemRs();
    purchaseItemRs.setPurchaseItem(toPurchaseItem(response));
    purchaseItemRs.setDescription(description);
    return purchaseItemRs;
  }

  /**
   * Возвращает транспротный объект ответа для пользователя.
   */
  public static SrvGetUserRs responseUser(User response, String description) {
    SrvGetUserRs userRs = new SrvGetUserRs();
    userRs.setUser(toUserInfo(response));
    userRs.setDescription(description);
    return userRs;
  }

  /**
   * Возвращает транспротный объект ответа для покупки.
   */
  public static SrvGetPurchaseRs responsePurchase(Purchase response) {
    SrvGetPurchaseRs purchaseRs = new SrvGetPurchaseRs();
    purchaseRs.setPurchase(toPurchaseInfo(response));
    return purchaseRs;
  }

  /**
   * Возвращает транспротный объект ответа для списка покупок.
   */
  public static SrvGetPurchaseListRs responsePurchaseList(List<Purchase> response) {
    List<PurchaseInfo> result = response.stream()
        .map(p -> toPurchaseInfo(p))
        .collect(Collectors.toList());

    SrvGetPurchaseListRs purchaseListRs = new SrvGetPurchaseListRs();
    purchaseListRs.getPurchase().addAll(result);
    return purchaseListRs;
  }


  //******** Methods *******

  private static PurchaseInfo toPurchaseInfo(Purchase purchase) {
    PurchaseInfo response = new PurchaseInfo();
    response.setId(purchase.getId());
    response.setName(purchase.getUser().getName());
    response.setLastName(purchase.getUser().getLastName());
    response.setAge(purchase.getUser().getAge());
    response.setPurchaseItem(toPurchaseItem(purchase.getItem()));
    response.setCount(purchase.getCount());
    response.setAmount(purchase.getAmount());
    response.setPurchaseDate(xmlCalendar(purchase.getDate()));
    return response;
  }

  private static PurchaseItem toPurchaseItem(Item item) {
    return PurchaseItem.fromValue(item.getName());
  }

  private static UserInfo toUserInfo(User user) {
    UserInfo response = new UserInfo();
    response.setId(user.getId());
    response.setName(user.getName());
    response.setLastName(user.getLastName());
    response.setAge(user.getAge());
    return response;
  }

  private static Date date(XMLGregorianCalendar xmlCalendar) {
    return (xmlCalendar != null) ? xmlCalendar.toGregorianCalendar().getTime() : null;
  }

  private static XMLGregorianCalendar xmlCalendar(Date date) {
    if (date == null) {
      return null;
    }
    try {
      GregorianCalendar calendar = new GregorianCalendar();
      calendar.setTime(date);
      return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
    } catch (DatatypeConfigurationException e) {
      log.error("XMLGregorianCalendar building error", e);
      return null;
    }
  }

}
