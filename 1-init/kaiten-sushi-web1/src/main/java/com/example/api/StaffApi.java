package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ordergroup.OrderGroup;
import com.example.model.ordergroup.OrderGroupDao;
import com.example.model.ordergroup.OrderGroupStatus;
import com.example.model.orderitem.OrderItem;
import com.example.model.orderitem.OrderItemDao;
import com.example.model.orderitem.OrderItemStatus;
import com.example.model.orderitem.OrderItemView;
import com.example.model.orderitem.OrderItemViewDao;

@RestController
@RequestMapping("staff/api")
public class StaffApi {

  private OrderGroupDao orderGroupDao;

  private OrderItemDao orderItemDao;

  private OrderItemViewDao orderItemViewDao;

  @Autowired
  public StaffApi(OrderGroupDao orderGroupDao, OrderItemDao orderItemDao,
      OrderItemViewDao orderItemViewDao) {
    this.orderGroupDao = orderGroupDao;
    this.orderItemDao = orderItemDao;
    this.orderItemViewDao = orderItemViewDao;
  }

  @RequestMapping(path = "orders/waiting", method = RequestMethod.GET)
  public List<OrderItemView> getOrderItems() {
    return orderItemViewDao.selectForStaff();
  }

  @RequestMapping(path = "orders/{orderItemId}/deliver", method = RequestMethod.POST)
  @Transactional
  public void deliverOrder(@PathVariable("orderItemId") int orderItemId) {
    OrderItem orderItem = orderItemDao.selectById(orderItemId);
    Assert.notNull(orderItem);
    Assert.state(orderItem.status == OrderItemStatus.ORDERED);

    orderItem.status = OrderItemStatus.DELIVERED;
    orderItemDao.update(orderItem);
  }

  @RequestMapping(path = "order-groups/checkout", method = RequestMethod.GET)
  public List<OrderGroup> getCheckoutOrderGroups() {
    return orderGroupDao.selectBillRequested();
  }

  @RequestMapping(path = "order-groups/{orderGroupId}/close", method = RequestMethod.POST)
  @Transactional
  public void closeOrderGroup(@PathVariable("orderGroupId") int orderGroupId) {
    OrderGroup orderGroup = orderGroupDao.selectById(orderGroupId);
    Assert.notNull(orderGroup);
    Assert.state(orderGroup.status == OrderGroupStatus.BILL_REQUESTED);

    orderGroup.status = OrderGroupStatus.CLOSED;
    orderGroupDao.update(orderGroup);
  }

}
