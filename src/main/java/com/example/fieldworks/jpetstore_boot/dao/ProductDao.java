package com.example.fieldworks.jpetstore_boot.dao;

import java.util.List;

import com.example.fieldworks.jpetstore_boot.domain.Product;
import org.springframework.dao.DataAccessException;

public interface ProductDao {

  List getProductListByCategory(String categoryId) throws DataAccessException;

  List searchProductList(String keywords) throws DataAccessException;

	Product getProduct(String productId) throws DataAccessException;

}
