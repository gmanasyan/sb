package ru.smartsoft.dto;

import java.text.DecimalFormat;
import java.util.Date;

public class PurchaseTo {
  private static final DecimalFormat df = new DecimalFormat("#.00");

  private Integer id;
  private Date date;
  private String name;
  private Double cost;
  private Integer count;
  private String buyerName;
  private String buyerSecondName;
  private Integer age;

  public PurchaseTo(Integer id, Date date, String name, Double cost, Integer count,
      String buyerName, String buyerSecondName, Integer age) {
    this.id = id;
    this.date = date;
    this.name = name;
    this.cost = cost;
    this.count = count;
    this.buyerName = buyerName;
    this.buyerSecondName = buyerSecondName;
    this.age = age;
  }

  public Integer getId() {
    return id;
  }

  public Date getDate() {
    return date;
  }

  public String getName() {
    return name;
  }

  public String getCost() {
    return df.format(cost);
  }

  public Integer getCount() {
    return count;
  }

  public String getBuyerName() {
    return buyerName;
  }

  public String getBuyerSecondName() {
    return buyerSecondName;
  }

  public Integer getAge() {
    return age;
  }
}
