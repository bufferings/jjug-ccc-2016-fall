package com.example.order.domain.event;

import java.util.List;

import lombok.Data;

@Data
public class OrderGroupClosedEvent implements DomainEvent {

  public final String orderGroupId;

  public final List<String> orderIds;

}
