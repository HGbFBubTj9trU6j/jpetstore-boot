package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.*;
import org.junit.jupiter.api.BeforeEach;
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
class CustomOrderDaoTest {

    @Autowired
    @Qualifier("customOrderDaoImpl")
    private CustomOrderDao customOrderDao;

    @Autowired
    private SequenceDao sequenceDao;

    private Order order;
    private Account account;
    private Cart cart;
    private Item item1;
    private Item item2;
    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        order = new Order();
        account = new Account();
        account.setUsername("testuser");
        account.setFirstName("John");
        account.setLastName("Doe");
        account.setAddress1("123 Main St");
        account.setAddress2("Apt 4B");
        account.setCity("Springfield");
        account.setState("IL");
        account.setZip("62701");
        account.setCountry("USA");

        cart = new Cart();
        item1 = new Item();
        item1.setItemId("item1");
        item1.setListPrice(10.0);

        item2 = new Item();
        item2.setItemId("item2");
        item2.setListPrice(20.0);

        cart.addItem(item1, true);
        cart.addItem(item2, true);

        cartItem = new CartItem();
        cartItem.setItem(item1);
        cartItem.setQuantity(2);
        cartItem.setInStock(true);
    }

    @Test
    void testInsertOrder() {
        // Set up the order object with necessary details
        order.initOrder(account, cart);
        customOrderDao.insertOrder(order);
        customOrderDao.insertOrder(order);

        // testGetOrdersByUsername
        List<Order> orders = customOrderDao.getOrdersByUsername(order.getUsername());
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
        assertEquals(2, orders.size());
        assertEquals(orders.get(0).getOrderId(), 1001);

        // testGetOrder
        Order insertedOrder = customOrderDao.getOrder(1001);
        assertNotNull(insertedOrder);
        assertEquals("testuser", insertedOrder.getUsername());
    }

}