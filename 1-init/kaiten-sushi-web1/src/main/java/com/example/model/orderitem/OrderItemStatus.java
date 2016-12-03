package com.example.model.orderitem;

import org.seasar.doma.Domain;

@Domain(valueType = Integer.class, factoryMethod = "of")
public enum OrderItemStatus {

  ORDERED(1), DELIVERED(2);

  private final Integer value;

  private OrderItemStatus(Integer value) {
    this.value = value;
  }

  public static OrderItemStatus of(Integer value) {
    for (OrderItemStatus status : OrderItemStatus.values()) {
      if (status.value.equals(value)) {
        return status;
      }
    }
    throw new IllegalArgumentException();
  }

  public Integer getValue() {
    return value;
  }

}