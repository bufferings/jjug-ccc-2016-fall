package com.example.order.domain.model.order;

import java.util.List;

public interface OrderRepository {

  void save(OrderGroup orderGroup);

  OrderGroup activeOrderGroupOf(TableNumber tableNumber);

  OrderGroup orderGroupOfId(OrderGroupId orderGroupId);

  List<OrderGroup> checkoutOrderGroups();

}
