package com.example.order.domain.event;

import lombok.Data;

@Data
public class OrderGroupClosedEvent implements DomainEvent {

  public final String orderGroupId;

}
