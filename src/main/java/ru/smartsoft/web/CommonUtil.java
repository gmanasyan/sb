package ru.smartsoft.web;

import ru.smartsoft.dto.PurchaseTo;
import ru.smartsoft.model.Purchase;

public class CommonUtil {
  public static PurchaseTo toDto(Purchase pr) {
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
}
