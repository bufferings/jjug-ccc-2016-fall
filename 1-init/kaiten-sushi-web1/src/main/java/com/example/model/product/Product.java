package com.example.model.product;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Version;

import lombok.ToString;

@Entity
@ToString
public class Product {

  @Id
  public Integer productId;

  public String productName;

  public Integer unitPrice;

  public Integer stockQuantity;

  @Version
  public Integer version;

}
