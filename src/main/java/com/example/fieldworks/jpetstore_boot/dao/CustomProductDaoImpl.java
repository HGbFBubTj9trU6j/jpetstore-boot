package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

@Repository
public class CustomProductDaoImpl implements CustomProductDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CustomProductDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getProductListByCategory(String categoryId) throws DataAccessException {
        String sql = "SELECT productid, name, descn, category FROM product WHERE category = :categoryId";
        Map<String, Object> params = new HashMap<>();
        params.put("categoryId", categoryId);
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public Product getProduct(String productId) throws DataAccessException {
        String sql = "SELECT productid, name, descn, category FROM product WHERE productid = :productId";
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);
        return jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public List<Product> searchProductList(String keywords) throws DataAccessException {
        String sql = "SELECT productid, name, descn, category FROM product WHERE lower(name) LIKE :keyword OR lower(category) LIKE :keyword OR lower(descn) LIKE :keyword";
        List<String> keywordList = new ArrayList<>();
        StringTokenizer splitter = new StringTokenizer(keywords, " ", false);
        while (splitter.hasMoreTokens()) {
            keywordList.add("%" + splitter.nextToken().toLowerCase() + "%");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", String.join(" OR ", keywordList));
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(Product.class));
    }
}