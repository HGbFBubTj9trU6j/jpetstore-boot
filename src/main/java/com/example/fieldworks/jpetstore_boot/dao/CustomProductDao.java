package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Product;

import java.util.List;

public interface CustomProductDao {

    List<Product> getProductListByCategory(String categoryId);

    List<Product> searchProductList(String keywords);

    Product getProduct(String productId);
}
