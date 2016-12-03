package com.example.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.api.view.OrderDelivered;
import com.example.api.view.OrderSeatView;
import com.example.api.view.OrderSeatViewDao;
import com.example.api.view.OrderStaffView;
import com.example.api.view.OrderStaffViewDao;
import com.example.order.domain.event.DomainEvent;
import com.example.order.domain.event.OrderCreatedEvent;
import com.example.order.domain.event.OrderDeliveredEvent;
import com.example.order.domain.event.OrderGroupClosedEvent;
import com.example.order.domain.event.StoredEvent;

@Component
public class Listener {

  private OrderSeatViewDao orderSeatViewDao;

  private OrderStaffViewDao orderStaffViewDao;

  @Autowired
  public Listener(OrderSeatViewDao orderSeatViewDao,
      OrderStaffViewDao orderStaffViewDao) {
    this.orderSeatViewDao = orderSeatViewDao;
    this.orderStaffViewDao = orderStaffViewDao;
  }

  @KafkaListener(id = "foo", topics = "topic1", group = "group1")
  public void listen(StoredEvent storedEvent) {
    DomainEvent event = storedEvent.toDomainEvent();
    if (event instanceof OrderCreatedEvent) {
      handleOrderCreatedEvent((OrderCreatedEvent) event);
    } else if (event instanceof OrderDeliveredEvent) {
      handleOrderDeliveredEvent((OrderDeliveredEvent) event);
    } else if (event instanceof OrderGroupClosedEvent) {
      handleOrderGroupClosedEvent((OrderGroupClosedEvent) event);
    }
  }

  private void handleOrderCreatedEvent(OrderCreatedEvent event) {
    OrderSeatView seatView = createSeatView(event);
    orderSeatViewDao.insert(seatView);

    OrderStaffView staffView = createStaffView(event);
    orderStaffViewDao.insert(staffView);
  }

  private OrderStaffView createStaffView(OrderCreatedEvent event) {
    OrderStaffView view = new OrderStaffView();
    view.orderId = event.orderId;
    view.orderGroupId = event.orderGroupId;
    view.productId = event.productId;
    view.productName = event.productName;
    view.quantity = event.quantity;
    view.tableNumber = event.tableNumber;
    view.orderDateTime = event.orderDateTime;
    return view;
  }

  private OrderSeatView createSeatView(OrderCreatedEvent event) {
    OrderSeatView view = new OrderSeatView();
    view.orderId = event.orderId;
    view.orderGroupId = event.orderGroupId;
    view.tableNumber = event.tableNumber;
    view.productId = event.productId;
    view.productName = event.productName;
    view.quantity = event.quantity;
    view.orderDateTime = event.orderDateTime;
    view.delivered = OrderDelivered.NOT_DELIVERED;
    return view;
  }

  private void handleOrderDeliveredEvent(OrderDeliveredEvent event) {
    OrderSeatView seatView = orderSeatViewDao.selectById(event.orderId);
    seatView.delivered = OrderDelivered.DELIVERED;
    orderSeatViewDao.update(seatView);

    OrderStaffView staffView = orderStaffViewDao.selectById(event.orderId);
    orderStaffViewDao.delete(staffView);
  }

  private void handleOrderGroupClosedEvent(OrderGroupClosedEvent event) {
    orderSeatViewDao.deleteByGroupId(event.orderGroupId);
    orderStaffViewDao.deleteByGroupId(event.orderGroupId);
  }
}