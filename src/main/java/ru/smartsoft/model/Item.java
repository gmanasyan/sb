package ru.smartsoft.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Сущьность товара.
 */
@Entity
@Table(name = "items")
public class Item extends AbstractBaseEntity {

  private String name;

  public Item() {
  }

  public Item(Integer id, String name) {
    super(id);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Item{" +
        "name='" + name + '\'' +
        ", id=" + id +
        '}';
  }
}
