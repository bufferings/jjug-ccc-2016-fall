package com.example.model.orderitem;

import java.time.LocalDateTime;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Version;

import lombok.ToString;

@Entity
@ToString
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer orderItemId;

  public Integer orderGroupId;

  public Integer productId;

  public Integer quantity;

  public LocalDateTime orderDateTime;

  public OrderItemStatus status;

  @Version
  public Integer version;

}
