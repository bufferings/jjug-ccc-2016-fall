package com.example.order.domain.model.product;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class ProductName {

  private String value;

  public ProductName(String value) {
    this.setValue(value);
  }

  private void setValue(String value) {
    Assert.isTrue(StringUtils.isNotEmpty(value));
    this.value = value;
  }

}