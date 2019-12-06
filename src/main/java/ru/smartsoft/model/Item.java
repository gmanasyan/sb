package ru.smartsoft.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "items")
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.PROPERTY)
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

  @Override
  public String toString() {
    return "Item{" +
        "name='" + name + '\'' +
        ", cost=" + cost +
        ", id=" + id +
        '}';
  }
}
