package com.example.order.domain.model.product;

import org.springframework.util.Assert;

import com.example.order.util.IdUtil;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class ProductId {

  private String value;

  public ProductId(String value) {
    this.setValue(value);
  }

  private void setValue(String value) {
    Assert.isTrue(IdUtil.matchesIdFormat(value));
    this.value = value;
  }

}
