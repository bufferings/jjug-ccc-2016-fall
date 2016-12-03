package com.example.api.view;

import org.seasar.doma.Domain;

@Domain(valueType = Integer.class, factoryMethod = "of")
public enum OrderDelivered {

  NOT_DELIVERED(0), DELIVERED(1);

  private final Integer value;

  private OrderDelivered(Integer value) {
    this.value = value;
  }

  public static OrderDelivered of(Integer value) {
    for (OrderDelivered status : OrderDelivered.values()) {
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