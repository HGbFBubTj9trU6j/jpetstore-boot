package com.example.fieldworks.jpetstore_boot.dao;

import java.util.List;

import com.example.fieldworks.jpetstore_boot.domain.Category;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category, String> {

	List getCategoryList() throws DataAccessException;

  Category getCategory(String categoryId) throws DataAccessException;

}
