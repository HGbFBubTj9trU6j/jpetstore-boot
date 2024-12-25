package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Category;
import com.example.fieldworks.jpetstore_boot.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJdbcTest
@ComponentScan(basePackages = "com.example.fieldworks.jpetstore_boot.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryDaoTest {
    @Autowired
    private CategoryDao sut;

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ItemDao itemDao;

    @Test
    void testFindById() {
        Category target = sut.findById("DOGS").orElseThrow();
        assertEquals("DOGS", target.getCategoryId());
        assertEquals("Dogs", target.getName());
        assertEquals("<image src=\"../resources/images/dogs_icon.gif\"><font size=\"5\" color=\"blue\"> Dogs</font>", target.getDescription());
    }

    @Test
    void testFindAll() {
        assertEquals(5, sut.findAll().size());
    }

    @Test
    void testSaveAsInsert() {
        assertEquals(5, sut.findAll().size());
        Category category = new Category();
        category.setCategoryId("newCATID");
        category.setName("newName");
        category.setDescription("newDescription");
        category.setNeedsInsert();

        sut.save(category);

        assertEquals(6, sut.findAll().size());
    }

    @Test
    void testSaveAsUpdate() {
        assertEquals(5, sut.findAll().size());
        Category category = sut.findById("DOGS").orElseThrow();
        assertEquals("Dogs", category.getName());
        assertEquals("<image src=\"../resources/images/dogs_icon.gif\"><font size=\"5\" color=\"blue\"> Dogs</font>", category.getDescription());

        category.setName("updDogs");;
        sut.save(category);

        assertEquals(5, sut.findAll().size());
        Category insertedCategory = sut.findById("DOGS").orElseThrow();
        assertEquals("updDogs", insertedCategory.getName());
    }

    @Test
    void testDelete() {
        List<Product> products = productDao.getProductListByCategory("DOGS");
        products.forEach(product -> {
            itemDao.getItemListByProduct(product.getProductId()).forEach(itemDao::delete);
            productDao.delete(product);
        });

        assertEquals(5, sut.findAll().size());
        Category category = sut.findById("DOGS").orElseThrow();
        assertEquals("Dogs", category.getName());

        sut.delete(category);

        assertEquals(4, sut.findAll().size());
    }

    @Test
    void testDeleteById() {
        List<Product> products = productDao.getProductListByCategory("DOGS");
        products.forEach(product -> {
            itemDao.getItemListByProduct(product.getProductId()).forEach(itemDao::delete);
            productDao.delete(product);
        });

        assertEquals(5, sut.findAll().size());
        Category category = sut.findById("DOGS").orElseThrow();
        assertEquals("Dogs", category.getName());

        sut.deleteById(category.getId());

        assertEquals(4, sut.findAll().size());
    }
}
