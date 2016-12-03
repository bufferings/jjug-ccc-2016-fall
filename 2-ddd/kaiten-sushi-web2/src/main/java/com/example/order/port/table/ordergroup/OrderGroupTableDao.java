package com.example.order.port.table.ordergroup;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

@ConfigAutowireable
@Dao
public interface OrderGroupTableDao {

  @Insert
  int insert(OrderGroupTable orderGroup);

  @Update
  int update(OrderGroupTable orderGroup);

  @Select
  OrderGroupTable selectById(String orderGroupId);

  @Select
  OrderGroupTable selectCurrentForTable(Integer tableNumber);

  @Select
  List<OrderGroupTable> selectCheckout();

}
