package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Category;

import java.util.List;

public interface CustomCategoryDao {

	List<Category> getCategoryList();

  Category getCategory(String categoryId);

}
