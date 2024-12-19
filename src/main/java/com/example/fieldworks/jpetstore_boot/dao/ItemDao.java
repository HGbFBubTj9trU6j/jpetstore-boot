package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Item;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends ListCrudRepository<Item, String>, CustomItemDao {

}
