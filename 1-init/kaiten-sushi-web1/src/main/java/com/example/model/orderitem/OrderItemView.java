package com.example.model.orderitem;

import java.time.LocalDateTime;

import org.seasar.doma.Entity;

import lombok.ToString;

@Entity
@ToString
public class OrderItemView {

  public Integer orderItemId;

  public Integer orderGroupId;

  public Integer tableNumber;

  public Integer productId;

  public String productName;

  public Integer quantity;

  public LocalDateTime orderDateTime;

  public OrderItemStatus orderItemStatus;

}
