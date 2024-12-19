package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Item;
import com.example.fieldworks.jpetstore_boot.domain.Order;

import java.util.List;

public interface CustomItemDao {

  void updateQuantity(Order order);

  boolean isItemInStock(String itemId);

  List<Item> getItemListByProduct(String productId);

  Item getItem(String itemId);

}
