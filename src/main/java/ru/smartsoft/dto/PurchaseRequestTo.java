package ru.smartsoft.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="purchaseRequest")
@XmlType(name = "purchaseRequest", namespace = "http://smartsoft.ru/sb",
    propOrder = {"itemName", "count"})
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseRequestTo {

  /**
   * Name of item.
   */
  @XmlElement(required = true, defaultValue = "requiredElementValue")
  private String itemName;

  /**
   * Count for item requested.
   */
  @XmlElement(required = true, defaultValue = "requiredElementValue")
  private Integer count;

  public PurchaseRequestTo() {
    super();
  }

  public PurchaseRequestTo(String itemName, Integer count) {
    this.itemName = itemName;
    this.count = count;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  @Override
  public String toString() {
    return "PurchaseRequestTo{" +
        "itemName='" + itemName + '\'' +
        ", count=" + count +
        '}';
  }
}
