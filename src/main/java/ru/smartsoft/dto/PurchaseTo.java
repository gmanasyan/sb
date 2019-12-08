package ru.smartsoft.dto;

import java.text.DecimalFormat;
import java.util.Date;

public class PurchaseTo {

  private Integer id;
  private Date date;
  private String item;
  private Integer count;
  private Integer amount;
  private String name;
  private String lastName;
  private Integer age;


  public PurchaseTo(Integer id, Date date, String item, Integer count, Integer amount,
      String name, String lastName, Integer age) {
    this.id = id;
    this.date = date;
    this.item = item;
    this.count = count;
    this.amount = amount;
    this.name = name;
    this.lastName = lastName;
    this.age = age;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
