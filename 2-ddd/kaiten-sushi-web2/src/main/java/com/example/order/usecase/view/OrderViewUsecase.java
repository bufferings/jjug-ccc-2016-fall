package com.example.order.usecase.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderViewUsecase {

  private OrderViewDao orderViewDao;

  @Autowired
  public OrderViewUsecase(OrderViewDao orderViewDao) {
    this.orderViewDao = orderViewDao;
  }

  public List<OrderView> selectForSeat(int tableNumber) {
    return orderViewDao.selectForSeat(tableNumber);
  }

  public List<OrderView> selectForStaff() {
    return orderViewDao.selectForStaff();
  }

}
