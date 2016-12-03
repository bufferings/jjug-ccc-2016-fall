package com.example.order.domain.model.product;

import java.util.List;

public interface ProductRepository {

  List<Product> products();

  Product productOfId(ProductId productId);

  void save(Product product);

}
