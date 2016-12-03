package com.example.order.domain.model.product;

import org.springframework.util.Assert;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class StockQuantity {

  private int value;

  public StockQuantity(int value) {
    this.setValue(value);
  }

  private void setValue(int value) {
    Assert.isTrue(value >= 0);
    this.value = value;
  }

}