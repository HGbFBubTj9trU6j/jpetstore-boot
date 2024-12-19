package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends ListCrudRepository<Product, String>, CustomProductDao {

}
