package com.example.order.port.table.orderitem;

import java.time.LocalDateTime;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.ToString;

@Entity
@Table(name = "OrderItem")
@ToString
public class OrderItemTable {

  @Id
  public String orderItemId;

  public String orderGroupId;

  public String productId;

  public Integer quantity;

  public LocalDateTime orderDateTime;

  public Integer status;

}
