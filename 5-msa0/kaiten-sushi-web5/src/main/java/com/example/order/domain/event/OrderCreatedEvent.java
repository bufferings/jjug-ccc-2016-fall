package com.example.order.domain.event;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderCreatedEvent implements DomainEvent {

  public final String orderId;

  public final String orderGroupId;

  public final Integer tableNumber;

  public final String productId;

  public final String productName;

  public final Integer quantity;

  public final LocalDateTime orderDateTime;

}
