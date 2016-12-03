package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.view.OrderStaffView;
import com.example.api.view.OrderStaffViewDao;
import com.example.order.domain.model.order.OrderGroup;
import com.example.order.usecase.OrderUsecase;

@RestController
@RequestMapping("staff/api")
public class StaffApi {

  private OrderUsecase orderUsecase;

  private OrderStaffViewDao orderStaffViewDao;

  @Autowired
  public StaffApi(OrderUsecase orderUsecase,
      OrderStaffViewDao orderStaffViewDao) {
    this.orderUsecase = orderUsecase;
    this.orderStaffViewDao = orderStaffViewDao;
  }

  @RequestMapping(path = "orders/waiting", method = RequestMethod.GET)
  public List<OrderStaffView> getOrders() {
    return orderStaffViewDao.selectForStaff();
  }

  @RequestMapping(path = "order-groups/{orderGroupId}/{orderId}/deliver", method = RequestMethod.POST)
  public void deliverOrder(@PathVariable("orderGroupId") String orderGroupId,
      @PathVariable("orderId") String orderId) {
    orderUsecase.deliver(orderGroupId, orderId);
  }

  @RequestMapping(path = "order-groups/checkout", method = RequestMethod.GET)
  public List<OrderGroup> getCheckoutOrderGroups() {
    return orderUsecase.checkoutOrderGroups();
  }

  @RequestMapping(path = "order-groups/{orderGroupId}/close", method = RequestMethod.POST)
  public void closeOrderGroup(
      @PathVariable("orderGroupId") String orderGroupId) {
    orderUsecase.close(orderGroupId);
  }

}
