package com.example.model.orderitem;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

@ConfigAutowireable
@Dao
public interface OrderItemViewDao {

  @Select
  List<OrderItemView> selectForSeat(Integer tableNumber);

  @Select
  List<OrderItemView> selectForStaff();

}
