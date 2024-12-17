package com.example.fieldworks.jpetstore_boot.dao;

import java.util.List;

import com.example.fieldworks.jpetstore_boot.domain.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<Product, String> {

  List getProductListByCategory(String categoryId) throws DataAccessException;

  List searchProductList(String keywords) throws DataAccessException;

	Product getProduct(String productId) throws DataAccessException;

}
