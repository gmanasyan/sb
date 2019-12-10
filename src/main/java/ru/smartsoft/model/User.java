package ru.smartsoft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Сущьность клиента.
 */
@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity {

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "lastname", nullable = false)
  private String lastName;

  @Column(name = "age", nullable = false)
  private Integer age;

  public User() {
  }

  public User(Integer id, String name, String lastName, Integer age) {
    super(id);
    this.name = name;
    this.lastName = lastName;
    this.age = age;
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
