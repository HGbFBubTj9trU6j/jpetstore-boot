package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Category;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomCategoryDaoImpl implements CustomCategoryDao {

    private final JdbcTemplate jdbcTemplate;

    public CustomCategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> getCategoryList() throws DataAccessException {
        String sql = "SELECT catid, name, descn FROM category";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Category category = new Category();
            category.setCategoryId(rs.getString("catid"));
            category.setName(rs.getString("name"));
            category.setDescription(rs.getString("descn"));
            return category;
        });
    }

    @Override
    public Category getCategory(String categoryId) throws DataAccessException {
        String sql = "SELECT catid, name, descn FROM category WHERE catid = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Category category = new Category();
            category.setCategoryId(rs.getString("catid"));
            category.setName(rs.getString("name"));
            category.setDescription(rs.getString("descn"));
            return category;
        }, categoryId);
    }
}