package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJdbcTest
@ComponentScan(basePackages = "com.example.fieldworks.jpetstore_boot.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductDaoTest {
    @Autowired
    private ProductDao sut;

    @Autowired
    private ItemDao itemDao;

    @Test
    void testFindById() {
        Product product = sut.findById("FI-SW-01").orElseThrow();
        assertEquals("FI-SW-01", product.getProductId());
        assertEquals("Angelfish", product.getName());
        assertEquals("FISH", product.getCategoryId());
        assertEquals("<image src=\"../resources/images/fish1.gif\">Salt Water fish from Australia", product.getDescription());
    }

    @Test
    void testFindAll() {
        assertEquals(16, sut.findAll().size());
    }

    @Test
    void testSaveAsInsert() {
        assertEquals(16, sut.findAll().size());
        Product product = new Product();
        product.setProductId("newProdID");
        product.setCategoryId("CATS");
        product.setName("newName");
        product.setDescription("newDescription");
        product.setNeedsInsert();

        sut.save(product);

        assertEquals(17, sut.findAll().size());
    }

    @Test
    void testSaveAsUpdate() {
        assertEquals(16, sut.findAll().size());
        Product product = sut.findById("FI-SW-01").orElseThrow();
        assertEquals("FI-SW-01", product.getProductId());
        assertEquals("Angelfish", product.getName());

        product.setName("updAngelFish");;
        sut.save(product);

        assertEquals(16, sut.findAll().size());

        Product insertedProduct = sut.findById("FI-SW-01").orElseThrow();
        assertEquals("updAngelFish", insertedProduct.getName());
    }

    @Test
    void testDelete() {
        assertEquals(16, sut.findAll().size());
        Product product = sut.findById("FI-SW-01").orElseThrow();
        itemDao.getItemListByProduct(product.getProductId()).forEach(itemDao::delete);

        sut.delete(product);

        assertEquals(15, sut.findAll().size());
    }

    @Test
    void testDeleteById() {
        assertEquals(16, sut.findAll().size());
        Product product = sut.findById("FI-SW-01").orElseThrow();
        itemDao.getItemListByProduct(product.getProductId()).forEach(itemDao::delete);

        sut.deleteById(product.getProductId());

        assertEquals(15, sut.findAll().size());
    }
}
