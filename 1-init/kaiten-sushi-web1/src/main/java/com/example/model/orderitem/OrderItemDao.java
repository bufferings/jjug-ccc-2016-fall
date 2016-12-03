package com.example.model.orderitem;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

@ConfigAutowireable
@Dao
public interface OrderItemDao {

  @Insert
  int insert(OrderItem orderItem);

  @Update
  int update(OrderItem orderItem);

  @Select
  OrderItem selectById(Integer orderItemId);

}
