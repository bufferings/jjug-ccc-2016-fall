package com.example.order.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.order.domain.model.product.Product;
import com.example.order.domain.model.product.ProductId;
import com.example.order.domain.model.product.ProductRepository;

@Service
@Transactional
public class ProductUsecase {

  private ProductRepository productRepository;

  @Autowired
  public ProductUsecase(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product getProduct(String productId) {
    return productRepository.productOfId(new ProductId(productId));
  }

  public List<Product> getProducts() {
    return productRepository.products();
  }

}
