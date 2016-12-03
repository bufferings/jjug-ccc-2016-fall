package com.example.api.view;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ConfigAutowireable
@Dao
public interface OrderSeatViewDao {

  @Insert
  int insert(OrderSeatView orderSeatView);

  @Update
  int update(OrderSeatView orderSeatView);

  @Select
  OrderSeatView selectById(String orderId);

  @Select
  List<OrderSeatView> selectByTableNumber(Integer tableNumber);

  @Delete(sqlFile = true)
  int deleteByGroupId(String orderGroupId);

}
