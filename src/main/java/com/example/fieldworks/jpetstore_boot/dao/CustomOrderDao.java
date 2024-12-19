package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Order;

import java.util.List;

public interface CustomOrderDao {

  List<Order> getOrdersByUsername(String username);

  Order getOrder(int orderId);

  void insertOrder(Order order);

}
