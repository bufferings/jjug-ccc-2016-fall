package com.example.order.domain.model.order;

import org.springframework.util.Assert;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class OrderQuantity {

  private static final int MAX_QUANTITY = 4;

  private static final int MIN_QUANTITY = 1;

  private int value;

  public OrderQuantity(int value) {
    this.setValue(value);
  }

  private void setValue(int value) {
    Assert.isTrue(value >= MIN_QUANTITY && value <= MAX_QUANTITY);
    this.value = value;
  }

}
