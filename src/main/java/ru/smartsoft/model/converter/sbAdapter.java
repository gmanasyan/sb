package ru.smartsoft.model.converter;

import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;
import ru.smartsoft.model.Item;
import ru.smartsoft.model.Purchase;
import ru.smartsoft.model.User;
import ru.smartsoft.model.sbxsd.SrvCreatePurchaseRq;

public class sbAdapter {

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

  private static Date date(XMLGregorianCalendar xmlCalendar) {
    return (xmlCalendar != null) ? xmlCalendar.toGregorianCalendar().getTime() : null;
  }

}
