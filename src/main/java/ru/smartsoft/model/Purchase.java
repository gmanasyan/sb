package ru.smartsoft.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Сущьность покупки клиента.
 */
@NamedQueries({
@NamedQuery(name = Purchase.ALL_SORTED,
    query = "SELECT p FROM Purchase p ORDER BY p.date DESC")
    })
@Entity
@Table(name = "purchases")
public class Purchase extends AbstractBaseEntity {
  public static final String ALL_SORTED = "Purchase.getAll";

  /**
   * Пользователь.
   */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;

  /**
   * Покупка.
   */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "item_id")
  private Item item;

  /**
   * Количество покупки.
   */
  @Column(name = "count", nullable = false)
  private Integer count;

  /**
   * Сумма покупки.
   */
  @Column(name = "amount", nullable = false)
  private Integer amount;

  /**
   * Дата покупки.
   */
  @Column(name = "date", nullable = false)
  private Date date;

  public Purchase() {
  }

  public Purchase(User user, Item item, Integer count, Integer amount, Date date) {
    this(null, user, item, count, amount, date);
  }

  public Purchase(Integer id, User user, Item item, Integer count,
      Integer amount, Date date) {
    super(id);
    this.user = user;
    this.item = item;
    this.amount = amount;
    this.count = count;
    this.date = date;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }
}
