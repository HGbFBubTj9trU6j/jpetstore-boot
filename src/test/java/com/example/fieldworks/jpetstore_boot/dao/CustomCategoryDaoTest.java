package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@ComponentScan(basePackages = "com.example.fieldworks.jpetstore_boot.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomCategoryDaoTest {

    @Autowired
    @Qualifier("customCategoryDaoImpl")
    private CustomCategoryDao customCategoryDao;

    @Test
    void testGetCategoryList() {
        List<Category> categories = customCategoryDao.getCategoryList();
        assertNotNull(categories);
        assertFalse(categories.isEmpty());
        assertEquals(5, categories.size());
        assertEquals("FISH", categories.get(0).getCategoryId());

    }

    @Test
    void testGetCategory() {
        Category category = customCategoryDao.getCategory("FISH");
        assertNotNull(category);
        assertEquals("FISH", category.getCategoryId());
    }

}