package com.example.model.ordergroup;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

@ConfigAutowireable
@Dao
public interface OrderGroupDao {

  @Insert
  int insert(OrderGroup orderGroup);

  @Update
  int update(OrderGroup orderGroup);

  @Select
  OrderGroup selectById(Integer orderGroupId);

  @Select
  OrderGroup selectCurrentForTable(Integer tableNumber);

  @Select
  List<OrderGroup> selectBillRequested();

}
