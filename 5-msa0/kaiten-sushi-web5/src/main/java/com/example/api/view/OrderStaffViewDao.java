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
public interface OrderStaffViewDao {

  @Insert
  int insert(OrderStaffView orderStaffView);

  @Update
  int update(OrderStaffView orderStaffView);

  @Select
  OrderStaffView selectById(String orderId);

  @Select
  List<OrderStaffView> selectForStaff();

  @Delete
  int delete(OrderStaffView orderStaffView);

  @Delete(sqlFile = true)
  int deleteByGroupId(String orderGroupId);

}
