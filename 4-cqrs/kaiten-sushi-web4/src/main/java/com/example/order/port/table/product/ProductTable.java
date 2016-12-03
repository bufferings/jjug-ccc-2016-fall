package com.example.order.port.table.product;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.Version;

import lombok.ToString;

@Entity
@Table(name = "Product")
@ToString
public class ProductTable {

  @Id
  public String productId;

  public String productName;

  public Integer stockQuantity;

  @Version
  public Integer version;

}
