package com.example.order.domain.model.order;

import java.time.LocalDateTime;

import org.springframework.util.Assert;

import com.example.order.domain.model.product.ProductId;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Order {

  public static Order newOrder(OrderId id, ProductId productId,
      OrderQuantity quantity) {
    return new Order(id, productId, quantity,
        new OrderDateTime(LocalDateTime.now()), OrderStatus.ORDERED);
  }

  public static Order restoreFromDataStore(OrderId id, ProductId productId,
      OrderQuantity quantity, OrderDateTime orderedOn, OrderStatus status) {
    return new Order(id, productId, quantity, orderedOn, status);
  }

  private OrderId id;

  private ProductId productId;

  private OrderQuantity quantity;

  private OrderDateTime orderedOn;

  private OrderStatus status;

  private Order(OrderId id, ProductId productId, OrderQuantity quantity,
      OrderDateTime orderedOn, OrderStatus status) {
    this.setId(id);
    this.setProductId(productId);
    this.setQuantity(quantity);
    this.setOrderedOn(orderedOn);
    this.setStatus(status);
  }

  public boolean canDeliver() {
    return this.getStatus() == OrderStatus.ORDERED;
  }

  public void deliver() {
    Assert.state(canDeliver());
    this.setStatus(OrderStatus.DELIVERED);
  }

  private void setId(OrderId id) {
    Assert.notNull(id);
    this.id = id;
  }

  private void setProductId(ProductId productId) {
    Assert.notNull(productId);
    this.productId = productId;
  }

  private void setQuantity(OrderQuantity quantity) {
    Assert.notNull(quantity);
    this.quantity = quantity;
  }

  private void setOrderedOn(OrderDateTime orderedOn) {
    Assert.notNull(orderedOn);
    this.orderedOn = orderedOn;
  }

  private void setStatus(OrderStatus status) {
    Assert.notNull(status);
    this.status = status;
  }

}
