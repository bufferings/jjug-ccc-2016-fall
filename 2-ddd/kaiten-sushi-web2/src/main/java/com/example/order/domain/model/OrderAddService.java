package com.example.order.domain.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.order.domain.model.order.OrderGroup;
import com.example.order.domain.model.order.OrderGroupId;
import com.example.order.domain.model.order.OrderQuantity;
import com.example.order.domain.model.order.OrderRepository;
import com.example.order.domain.model.order.TableNumber;
import com.example.order.domain.model.product.Product;
import com.example.order.domain.model.product.ProductId;
import com.example.order.domain.model.product.ProductRepository;
import com.example.order.util.IdUtil;

@Service
public class OrderAddService {

  private OrderRepository orderRepository;

  private ProductRepository productRepository;

  @Autowired
  public OrderAddService(OrderRepository orderRepository,
      ProductRepository productRepository) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
  }

  public void addOrder(TableNumber tableNumber, ProductId productId,
      OrderQuantity quantity) {

    Product product = productRepository.productOfId(productId);
    Assert.notNull(product);
    Assert.isTrue(product.canKeepStockForOrder(quantity));

    OrderGroup orderGroup = orderRepository.activeOrderGroupOf(tableNumber);
    if (orderGroup == null) {
      OrderGroupId orderGroupId = new OrderGroupId(IdUtil.generateId());
      orderGroup = OrderGroup.newOrderGroup(orderGroupId, tableNumber);
    }

    orderGroup.addOrder(productId, quantity);
    product.keepStockForOrder(quantity);

    orderRepository.save(orderGroup);
    productRepository.save(product);
  }
}
