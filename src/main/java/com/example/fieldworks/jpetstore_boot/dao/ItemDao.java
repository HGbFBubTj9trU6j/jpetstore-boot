package com.example.fieldworks.jpetstore_boot.dao;

import java.util.List;

import com.example.fieldworks.jpetstore_boot.domain.Item;
import com.example.fieldworks.jpetstore_boot.domain.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

public interface ItemDao extends CrudRepository<Item, String> {

  public void updateQuantity(Order order) throws DataAccessException;

  boolean isItemInStock(String itemId) throws DataAccessException;

  List getItemListByProduct(String productId) throws DataAccessException;

  Item getItem(String itemId) throws DataAccessException;

}
