package com.example.order.port.table.product;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

@ConfigAutowireable
@Dao
public interface ProductTableDao {

  @Update
  int update(ProductTable product);

  @Select
  List<ProductTable> selectAll();

  @Select
  ProductTable selectById(String productId);

}
