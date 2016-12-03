package com.example.model.ordergroup;

import org.seasar.doma.Domain;

@Domain(valueType = Integer.class, factoryMethod = "of")
public enum OrderGroupStatus {

  OPENED(1), BILL_REQUESTED(2), CLOSED(3);

  private final Integer value;

  private OrderGroupStatus(Integer value) {
    this.value = value;
  }

  public static OrderGroupStatus of(Integer value) {
    for (OrderGroupStatus status : OrderGroupStatus.values()) {
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