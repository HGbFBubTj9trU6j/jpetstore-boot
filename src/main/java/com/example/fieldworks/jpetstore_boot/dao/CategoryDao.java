package com.example.fieldworks.jpetstore_boot.dao;

import java.util.List;

import com.example.fieldworks.jpetstore_boot.domain.Category;
import org.springframework.dao.DataAccessException;

public interface CategoryDao {

	List getCategoryList() throws DataAccessException;

  Category getCategory(String categoryId) throws DataAccessException;

}
