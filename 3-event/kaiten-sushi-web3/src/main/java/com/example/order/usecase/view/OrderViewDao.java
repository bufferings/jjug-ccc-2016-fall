package com.example.order.usecase.view;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

@ConfigAutowireable
@Dao
public interface OrderViewDao {

  @Select
  List<OrderView> selectForSeat(Integer tableNumber);

  @Select
  List<OrderView> selectForStaff();

}
