package com.example.order.domain.model.product;

import org.springframework.util.Assert;

import com.example.order.domain.model.order.OrderQuantity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Product {

  public static Product restoreFromDataStore(ProductId id, ProductName name,
      StockQuantity stockQuantity, Integer version) {
    Assert.notNull(version);
    Assert.isTrue(version >= 1);
    return new Product(id, name, stockQuantity, version);
  }

  private ProductId id;

  private ProductName name;

  private StockQuantity stockQuantity;

  private Integer version;

  private Product(ProductId id, ProductName name, StockQuantity stockQuantity,
      Integer version) {
    this.setId(id);
    this.setName(name);
    this.setStockQuantity(stockQuantity);
    this.setVersion(version);
  }

  public boolean canKeepStockForOrder(OrderQuantity quantity) {
    Assert.notNull(quantity);
    return this.getStockQuantity().getValue() >= quantity.getValue();
  }

  public void keepStockForOrder(OrderQuantity quantity) {
    Assert.state(canKeepStockForOrder(quantity));
    this.setStockQuantity(new StockQuantity(
        this.getStockQuantity().getValue() - quantity.getValue()));
  }

  public boolean isNew() {
    return this.getVersion() == -1;
  }

  public void incrementVersion() {
    if (isNew()) {
      this.setVersion(1);
    } else {
      this.setVersion(this.getVersion().intValue() + 1);
    }
  }

  private void setId(ProductId id) {
    Assert.notNull(id);
    this.id = id;
  }

  private void setName(ProductName name) {
    Assert.notNull(name);
    this.name = name;
  }

  private void setStockQuantity(StockQuantity stockQuantity) {
    Assert.notNull(stockQuantity);
    this.stockQuantity = stockQuantity;
  }

  private void setVersion(Integer version) {
    Assert.notNull(version);
    this.version = version;
  }

}
