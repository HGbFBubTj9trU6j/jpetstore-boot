package com.example.fieldworks.jpetstore_boot.dao;

import java.util.List;

import com.example.fieldworks.jpetstore_boot.domain.Item;
import com.example.fieldworks.jpetstore_boot.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends CrudRepository<Item, String> {

  public void updateQuantity(Order order);

  boolean isItemInStock(String itemId);

  List getItemListByProduct(String productId);

  Item getItem(String itemId);

}
