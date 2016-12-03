package com.example.order.domain.event;

import lombok.Data;

@Data
public class OrderDeliveredEvent implements DomainEvent {

  public final String orderGroupId;

  public final String orderId;

}
