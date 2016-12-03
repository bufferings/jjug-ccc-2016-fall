package com.example.order.port;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.order.domain.model.product.Product;
import com.example.order.domain.model.product.ProductId;
import com.example.order.domain.model.product.ProductName;
import com.example.order.domain.model.product.ProductRepository;
import com.example.order.domain.model.product.StockQuantity;
import com.example.order.port.table.product.ProductTable;
import com.example.order.port.table.product.ProductTableDao;

@Repository
public class DomaProductRepository implements ProductRepository {

  private ProductTableDao productTableDao;

  @Autowired
  public DomaProductRepository(ProductTableDao productTableDao) {
    this.productTableDao = productTableDao;
  }

  @Override
  public List<Product> products() {
    List<ProductTable> productRecords = productTableDao.selectAll();
    List<Product> products = new ArrayList<>();
    for (ProductTable record : productRecords) {
      products.add(productFrom(record));
    }
    return products;
  }

  @Override
  public Product productOfId(ProductId productId) {
    ProductTable productRecord = productTableDao
        .selectById(productId.getValue());
    return productFrom(productRecord);
  }

  @Override
  public void save(Product product) {
    productTableDao.update(productRecordFrom(product));
    product.incrementVersion();
  }

  private Product productFrom(ProductTable record) {
    return Product.restoreFromDataStore(new ProductId(record.productId),
        new ProductName(record.productName),
        new StockQuantity(record.stockQuantity), record.version);
  }

  private ProductTable productRecordFrom(Product product) {
    ProductTable record = new ProductTable();
    record.productId = product.getId().getValue();
    record.productName = product.getName().getValue();
    record.stockQuantity = product.getStockQuantity().getValue();
    record.version = product.getVersion();
    return record;
  }
}
