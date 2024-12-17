package com.example.fieldworks.jpetstore_boot.dao;

import java.util.List;

import com.example.fieldworks.jpetstore_boot.domain.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

public interface OrderDao extends CrudRepository<Order, String> {

  List getOrdersByUsername(String username) throws DataAccessException;

  Order getOrder(int orderId) throws DataAccessException;

  void insertOrder(Order order) throws DataAccessException;

}
