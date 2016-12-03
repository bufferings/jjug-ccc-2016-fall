package com.example.order.port.table.ordergroup;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.Version;

import lombok.ToString;

@Entity
@Table(name = "OrderGroup")
@ToString
public class OrderGroupTable {

  @Id
  public String orderGroupId;

  public Integer tableNumber;

  public Integer status;

  @Version
  public Integer version;

}
