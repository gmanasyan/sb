package ru.smartsoft.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import org.springframework.data.domain.Persistable;

@MappedSuperclass
public abstract class AbstractBaseEntity  {
  public static final int START_SEQ = 100000;

  @Id
  @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
  @Column(name = "id")
  protected Integer id;

  public AbstractBaseEntity() {
  }

  public AbstractBaseEntity(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


}
