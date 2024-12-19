package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Item;
import com.example.fieldworks.jpetstore_boot.domain.Order;
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
class CustomItemDaoTest {

    @Autowired
    @Qualifier("customItemDaoImpl")
    private CustomItemDao customItemDao;

    @Test
    void testGetItemListByProduct() {
        List<Item> items = customItemDao.getItemListByProduct("FI-SW-01");
        assertNotNull(items);
        assertFalse(items.isEmpty());
    }

    @Test
    void testGetItem() {
        Item item = customItemDao.getItem("EST-1");
        assertNotNull(item);
        assertEquals("EST-1", item.getItemId());
    }

    @Test
    void testUpdateQuantity() {
        Order order = new Order();
        // Set up the order object with necessary details
        customItemDao.updateQuantity(order);
        // Add assertions to verify the quantity update if possible
    }

    @Test
    void testIsItemInStock() {
        assertTrue(customItemDao.isItemInStock("EST-1"));
    }
}