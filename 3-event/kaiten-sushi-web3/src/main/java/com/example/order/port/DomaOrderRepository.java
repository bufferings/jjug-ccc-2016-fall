package com.example.order.port;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.order.domain.model.order.Order;
import com.example.order.domain.model.order.OrderDateTime;
import com.example.order.domain.model.order.OrderGroup;
import com.example.order.domain.model.order.OrderGroupId;
import com.example.order.domain.model.order.OrderGroupStatus;
import com.example.order.domain.model.order.OrderId;
import com.example.order.domain.model.order.OrderQuantity;
import com.example.order.domain.model.order.OrderRepository;
import com.example.order.domain.model.order.OrderStatus;
import com.example.order.domain.model.order.TableNumber;
import com.example.order.domain.model.product.ProductId;
import com.example.order.port.table.ordergroup.OrderGroupTable;
import com.example.order.port.table.ordergroup.OrderGroupTableDao;
import com.example.order.port.table.orderitem.OrderItemTable;
import com.example.order.port.table.orderitem.OrderItemTableDao;

@Repository
public class DomaOrderRepository implements OrderRepository {

  private OrderGroupTableDao orderGroupTableDao;

  private OrderItemTableDao orderItemTableDao;

  @Autowired
  public DomaOrderRepository(OrderGroupTableDao orderGroupTableDao,
      OrderItemTableDao orderItemTableDao) {
    this.orderGroupTableDao = orderGroupTableDao;
    this.orderItemTableDao = orderItemTableDao;
  }

  @Override
  public void save(OrderGroup orderGroup) {
    OrderGroupTable orderGroupRecord = orderGroupRecordFrom(orderGroup);
    List<OrderItemTable> orderItemRecords;
    if (orderGroup.isNew()) {
      orderGroupTableDao.insert(orderGroupRecord);
      orderItemRecords = new ArrayList<>();
    } else {
      orderGroupTableDao.update(orderGroupRecord);
      orderItemRecords = orderItemTableDao
          .selectByOrderGroupid(orderGroup.getId().getValue());
    }

    for (Order order : orderGroup.getOrders()) {
      OrderItemTable orderItemRecord = orderItemRecordFrom(orderGroup.getId(),
          order);
      if (exists(orderItemRecord, orderItemRecords)) {
        orderItemTableDao.update(orderItemRecord);
      } else {
        orderItemTableDao.insert(orderItemRecord);
      }
    }

    orderGroup.incrementVersion();
  }

  @Override
  public OrderGroup activeOrderGroupOf(TableNumber tableNumber) {
    OrderGroupTable orderGroupRecord = orderGroupTableDao
        .selectCurrentForTable(tableNumber.getValue());
    if (orderGroupRecord == null) {
      return null;
    }
    List<OrderItemTable> orderRecords = orderItemTableDao
        .selectByOrderGroupid(orderGroupRecord.orderGroupId);
    return orderGroupFrom(orderGroupRecord, orderRecords);
  }

  @Override
  public OrderGroup orderGroupOfId(OrderGroupId orderGroupId) {
    OrderGroupTable orderGroupRecord = orderGroupTableDao
        .selectById(orderGroupId.getValue());
    if (orderGroupRecord == null) {
      return null;
    }
    List<OrderItemTable> orderRecords = orderItemTableDao
        .selectByOrderGroupid(orderGroupRecord.orderGroupId);
    return orderGroupFrom(orderGroupRecord, orderRecords);
  }

  @Override
  public List<OrderGroup> checkoutOrderGroups() {
    List<OrderGroup> result = new ArrayList<>();

    List<OrderGroupTable> orderGroupRecords = orderGroupTableDao
        .selectCheckout();
    for (OrderGroupTable orderGroupRecord : orderGroupRecords) {
      List<OrderItemTable> orderRecords = orderItemTableDao
          .selectByOrderGroupid(orderGroupRecord.orderGroupId);
      OrderGroup orderGroup = orderGroupFrom(orderGroupRecord, orderRecords);
      result.add(orderGroup);
    }
    return result;
  }

  private OrderGroup orderGroupFrom(OrderGroupTable orderGroupRecord,
      List<OrderItemTable> orderRecords) {
    List<Order> orders = new ArrayList<>();
    for (OrderItemTable orderRecord : orderRecords) {
      orders.add(orderFrom(orderRecord));
    }
    return OrderGroup.restoreFromDataStore(
        new OrderGroupId(orderGroupRecord.orderGroupId),
        new TableNumber(orderGroupRecord.tableNumber),
        orderGroupStatusFrom(orderGroupRecord.status), orders,
        orderGroupRecord.version);
  }

  private OrderGroupStatus orderGroupStatusFrom(Integer status) {
    if (status == 1) {
      return OrderGroupStatus.OPENED;
    } else if (status == 2) {
      return OrderGroupStatus.CHECKOUT;
    } else if (status == 3) {
      return OrderGroupStatus.CLOSED;
    } else {
      throw new RuntimeException("Unknown status.");
    }
  }

  private Order orderFrom(OrderItemTable orderRecord) {
    return Order.restoreFromDataStore(new OrderId(orderRecord.orderItemId),
        new ProductId(orderRecord.productId),
        new OrderQuantity(orderRecord.quantity),
        new OrderDateTime(orderRecord.orderDateTime),
        orderStatusFrom(orderRecord.status));
  }

  private OrderStatus orderStatusFrom(Integer status) {
    if (status == 1) {
      return OrderStatus.ORDERED;
    } else if (status == 2) {
      return OrderStatus.DELIVERED;
    } else {
      throw new RuntimeException("Unknown status.");
    }
  }

  private OrderGroupTable orderGroupRecordFrom(OrderGroup orderGroup) {
    OrderGroupTable record = new OrderGroupTable();
    record.orderGroupId = orderGroup.getId().getValue();
    record.tableNumber = orderGroup.getTableNumber().getValue();
    record.status = orderGroupRecordStatusFrom(orderGroup.getStatus());
    record.version = orderGroup.getVersion();
    return record;
  }

  private Integer orderGroupRecordStatusFrom(OrderGroupStatus status) {
    switch (status) {
    case OPENED:
      return 1;
    case CHECKOUT:
      return 2;
    case CLOSED:
      return 3;
    default:
      throw new RuntimeException("Unknown status.");
    }
  }

  private OrderItemTable orderItemRecordFrom(OrderGroupId orderGroupId,
      Order order) {
    OrderItemTable orderItemRecord = new OrderItemTable();
    orderItemRecord.orderItemId = order.getId().getValue();
    orderItemRecord.orderGroupId = orderGroupId.getValue();
    orderItemRecord.productId = order.getProductId().getValue();
    orderItemRecord.orderDateTime = order.getOrderedOn().getValue();
    orderItemRecord.quantity = order.getQuantity().getValue();
    orderItemRecord.status = orderItemRecordStatusFrom(order.getStatus());
    return orderItemRecord;
  }

  private Integer orderItemRecordStatusFrom(OrderStatus status) {
    switch (status) {
    case ORDERED:
      return 1;
    case DELIVERED:
      return 2;
    default:
      throw new RuntimeException("Unknown status.");
    }
  }

  private boolean exists(OrderItemTable target,
      List<OrderItemTable> existingOrderItemRecords) {
    for (OrderItemTable record : existingOrderItemRecords) {
      if (record.orderItemId.equals(target.orderItemId)) {
        return true;
      }
    }
    return false;
  }

}
