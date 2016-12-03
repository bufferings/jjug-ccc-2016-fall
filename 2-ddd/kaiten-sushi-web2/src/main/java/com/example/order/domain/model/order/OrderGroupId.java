package com.example.order.domain.model.order;

import org.springframework.util.Assert;

import com.example.order.util.IdUtil;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class OrderGroupId {

  private String value;

  public OrderGroupId(String value) {
    this.setValue(value);
  }

  private void setValue(String value) {
    Assert.isTrue(IdUtil.matchesIdFormat(value));
    this.value = value;
  }

}
