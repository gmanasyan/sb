package ru.smartsoft.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item extends AbstractBaseEntity {

  private String name;
  private Integer cost;

  public Item() {
  }

  public Item(Integer id, String name, Integer cost) {
    super(id);
    this.name = name;
    this.cost = cost;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCost() {
    return cost;
  }

  public void setCost(Integer cost) {
    this.cost = cost;
  }
}
