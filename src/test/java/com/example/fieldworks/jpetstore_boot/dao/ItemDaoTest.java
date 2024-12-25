package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Item;
import com.example.fieldworks.jpetstore_boot.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJdbcTest
@ComponentScan(basePackages = "com.example.fieldworks.jpetstore_boot.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemDaoTest {
    @Autowired
    private ItemDao sut;

    @Test
    void testFindById() {
        Item item = sut.findById("EST-1").orElseThrow();
        assertEquals("EST-1", item.getItemId());
        assertEquals("FI-SW-01", item.getProductId());
        assertEquals(16.5, item.getListPrice());
        assertEquals(10, item.getUnitCost());
        assertEquals(1, item.getSupplierId());
        assertEquals("P", item.getStatus());
        assertEquals("Large", item.getAttribute1());
        assertNull(item.getAttribute2());
        assertNull(item.getAttribute3());
        assertNull(item.getAttribute4());
        assertNull(item.getAttribute5());
    }

    @Test
    void testFindAll() {
        assertEquals(28, sut.findAll().size());
    }

    @Test
    void testSaveAsInsert() {
        assertEquals(28, sut.findAll().size());
        Item item = new Item();
        item.setItemId("newItemID");
        item.setProductId("FI-SW-01");
        item.setListPrice(16.9);
        item.setUnitCost(9);
        item.setSupplierId(2);
        item.setStatus("iP");
        item.setAttribute1("iLarge");
        item.setNeedsInsert();

        sut.save(item);

        assertEquals(29, sut.findAll().size());
    }

    @Test
    void testSaveAsUpdate() {
        assertEquals(28, sut.findAll().size());
        Item item = sut.findById("EST-1").orElseThrow();
        assertEquals("P", item.getStatus());

        item.setStatus("U");
        sut.save(item);

        assertEquals(28, sut.findAll().size());
    }

    @Test
    void testDelete() {
        assertEquals(28, sut.findAll().size());
        Item item = sut.findById("EST-1").orElseThrow();
        sut.delete(item);
        assertEquals(27, sut.findAll().size());
    }

    @Test
    void testDeleteById() {
        assertEquals(28, sut.findAll().size());
        Item item = sut.findById("EST-1").orElseThrow();
        sut.deleteById(item.getId());
        assertEquals(27, sut.findAll().size());
    }
}
