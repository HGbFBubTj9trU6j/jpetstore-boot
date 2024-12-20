package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@ComponentScan(basePackages = "com.example.fieldworks.jpetstore_boot.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomProductDaoTest {

    @Autowired
    @Qualifier("customProductDaoImpl")
    private CustomProductDao customProductDao;

    @Test
    void testGetProductListByCategory() {
        List<Product> products = customProductDao.getProductListByCategory("FISH");
        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(4, products.size());
        assertEquals("FI-SW-01", products.get(0).getProductId());
        assertEquals("Angelfish", products.get(0).getName());
        assertEquals("FISH", products.get(0).getCategoryId());
        assertEquals("<image src=\"../resources/images/fish1.gif\">Salt Water fish from Australia", products.get(0).getDescription());
    }

    @Test
    void testGetProductListByCategoryNotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> customProductDao.getProductListByCategory("NotFound"));
    }

    @Test
    void testGetProduct() {
        Product product = customProductDao.getProduct("FI-SW-01");
        assertNotNull(product);
        assertEquals("FI-SW-01", product.getProductId());
        assertEquals("Angelfish", product.getName());
        assertEquals("FISH", product.getCategoryId());
        assertEquals("<image src=\"../resources/images/fish1.gif\">Salt Water fish from Australia", product.getDescription());
    }

    @Test
    void testSearchProductList() {
        List<Product> products = customProductDao.searchProductList("Fish");
        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(4, products.size());
        assertEquals("FI-SW-01", products.get(0).getProductId());
        assertEquals("Angelfish", products.get(0).getName());
        assertEquals("FISH", products.get(0).getCategoryId());
        assertEquals("<image src=\"../resources/images/fish1.gif\">Salt Water fish from Australia", products.get(0).getDescription());
    }
}