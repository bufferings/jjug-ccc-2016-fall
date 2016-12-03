package com.example.model.ordergroup;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Version;

import lombok.ToString;

@Entity
@ToString
public class OrderGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer orderGroupId;

  public Integer tableNumber;

  public OrderGroupStatus status;

  @Version
  public Integer version;

}
