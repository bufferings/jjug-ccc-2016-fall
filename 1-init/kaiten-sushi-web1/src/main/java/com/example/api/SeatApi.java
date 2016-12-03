package com.example.api;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ordergroup.OrderGroup;
import com.example.model.ordergroup.OrderGroupDao;
import com.example.model.ordergroup.OrderGroupStatus;
import com.example.model.orderitem.OrderItem;
import com.example.model.orderitem.OrderItemDao;
import com.example.model.orderitem.OrderItemStatus;
import com.example.model.orderitem.OrderItemView;
import com.example.model.orderitem.OrderItemViewDao;
import com.example.model.product.Product;
import com.example.model.product.ProductDao;

@RestController
@RequestMapping("seat/api")
public class SeatApi {

  private static final int MAX_QUANTITY = 4;

  private static final int MIN_QUANTITY = 1;

  private static final int DUMMY_TABLE_NUMBER = 21;

  private ProductDao productDao;

  private OrderGroupDao orderGroupDao;

  private OrderItemDao orderItemDao;

  private OrderItemViewDao orderItemViewDao;

  @Autowired
  public SeatApi(ProductDao productDao, OrderGroupDao orderGroupDao,
      OrderItemDao orderItemDao, OrderItemViewDao orderItemViewDao) {
    this.productDao = productDao;
    this.orderGroupDao = orderGroupDao;
    this.orderItemDao = orderItemDao;
    this.orderItemViewDao = orderItemViewDao;
  }

  @RequestMapping(path = "products", method = RequestMethod.GET)
  public List<Product> getProducts() {
    return productDao.selectAll();
  }

  @RequestMapping(path = "products/{productId}", method = RequestMethod.GET)
  public Product getProduct(@PathVariable("productId") Integer productId) {
    return productDao.selectById(productId);
  }

  @RequestMapping(path = "orders", method = RequestMethod.GET)
  public List<OrderItemView> getOrders() {
    int tableNumber = DUMMY_TABLE_NUMBER;
    return orderItemViewDao.selectForSeat(tableNumber);
  }

  @RequestMapping(path = "orders/add", method = RequestMethod.POST)
  @Transactional
  public void addOrder(@RequestParam("productId") int productId,
      @RequestParam("quantity") int quantity) {
    int tableNumber = DUMMY_TABLE_NUMBER;

    Assert.isTrue(quantity >= MIN_QUANTITY && quantity <= MAX_QUANTITY);

    Product product = productDao.selectById(productId);
    Assert.notNull(product);
    Assert.isTrue(product.stockQuantity >= quantity);

    OrderGroup orderGroup = orderGroupDao.selectCurrentForTable(tableNumber);
    if (orderGroup == null) {
      orderGroup = new OrderGroup();
      orderGroup.tableNumber = tableNumber;
      orderGroup.status = OrderGroupStatus.OPENED;
      orderGroupDao.insert(orderGroup);
    }
    Assert.state(orderGroup.status == OrderGroupStatus.OPENED);

    OrderItem orderItem = new OrderItem();
    orderItem.orderGroupId = orderGroup.orderGroupId;
    orderItem.productId = productId;
    orderItem.quantity = quantity;
    orderItem.orderDateTime = LocalDateTime.now();
    orderItem.status = OrderItemStatus.ORDERED;
    orderItemDao.insert(orderItem);

    product.stockQuantity -= quantity;
    productDao.update(product);
  }

  @RequestMapping(path = "checkout", method = RequestMethod.POST)
  @Transactional
  public void checkout() {
    int tableNumber = DUMMY_TABLE_NUMBER;

    OrderGroup orderGroup = orderGroupDao.selectCurrentForTable(tableNumber);
    Assert.notNull(orderGroup);
    Assert.state(orderGroup.status == OrderGroupStatus.OPENED);

    orderGroup.status = OrderGroupStatus.BILL_REQUESTED;
    orderGroupDao.update(orderGroup);
  }

}
