package com.example.model.product;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

@ConfigAutowireable
@Dao
public interface ProductDao {

  @Update
  int update(Product product);

  @Select
  List<Product> selectAll();

  @Select
  Product selectById(Integer productId);

}
