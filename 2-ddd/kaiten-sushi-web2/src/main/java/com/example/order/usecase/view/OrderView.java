package com.example.order.usecase.view;

import java.time.LocalDateTime;

import org.seasar.doma.Entity;

import lombok.ToString;

@Entity
@ToString
public class OrderView {

  public String orderId;

  public String orderGroupId;

  public Integer tableNumber;

  public String productId;

  public String productName;

  public Integer quantity;

  public LocalDateTime orderDateTime;

  public Integer orderItemStatus;

}
