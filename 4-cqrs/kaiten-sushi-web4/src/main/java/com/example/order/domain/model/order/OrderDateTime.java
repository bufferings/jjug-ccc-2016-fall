package com.example.order.domain.model.order;

import java.time.LocalDateTime;

import org.springframework.util.Assert;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class OrderDateTime {

  private LocalDateTime value;

  public OrderDateTime(LocalDateTime value) {
    this.setValue(value);
  }

  private void setValue(LocalDateTime value) {
    Assert.notNull(value);
    this.value = value;
  }

}