package com.example.order.port.table.orderitem;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

@ConfigAutowireable
@Dao
public interface OrderItemTableDao {

  @Insert
  int insert(OrderItemTable orderItem);

  @Update
  int update(OrderItemTable orderItem);

  @Select
  OrderItemTable selectById(String orderItemId);

  @Select
  List<OrderItemTable> selectByOrderGroupid(String orderGroupId);

}
