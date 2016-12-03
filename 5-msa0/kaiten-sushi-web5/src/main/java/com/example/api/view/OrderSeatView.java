package com.example.api.view;

import java.time.LocalDateTime;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;

import lombok.ToString;

@Entity
@ToString
public class OrderSeatView {

  @Id
  public String orderId;

  public String orderGroupId;

  public Integer tableNumber;

  public String productId;

  public String productName;

  public Integer quantity;

  public LocalDateTime orderDateTime;

  public OrderDelivered delivered;

}
