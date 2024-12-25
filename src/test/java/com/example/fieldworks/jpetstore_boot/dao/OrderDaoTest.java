package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.*;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
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
public class OrderDaoTest {
    @Autowired
    private OrderDao sut;

    @Autowired
    private SequenceDao sequenceDao;

    private Order order;
    private Account account;
    private Cart cart;

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
        Item item1 = new Item();
        item1.setItemId("item1");
        item1.setListPrice(10.0);

        Item item2 = new Item();
        item2.setItemId("item2");
        item2.setListPrice(20.0);

        cart.addItem(item1, true);
        cart.addItem(item2, true);
    }

    @Test
    void testFindById() {
        assertEquals(0, sut.findAll().size());

        order.initOrder(account, cart);
        order.setOrderId(sequenceDao.getNextId("ordernum"));
        order.setNeedsInsert();
        sut.save(order);

        assertEquals(1, sut.findAll().size());

        Order order = sut.findById(1001).orElseThrow();
        assertEquals(1001, order.getOrderId());
        assertEquals("testuser", order.getUsername());
    }

    @Test
    void testFindAll() {
        assertEquals(0, sut.findAll().size());
    }

    @Ignore
    @Test
    void testSaveAsInsert() {
    }

    @Test
    void testSaveAsUpdate() {
        assertEquals(0, sut.findAll().size());

        order.initOrder(account, cart);
        order.setOrderId(sequenceDao.getNextId("ordernum"));
        order.setNeedsInsert();
        sut.save(order);

        assertEquals(1, sut.findAll().size());

        Order order = sut.findById(1001).orElseThrow();
        assertEquals(1001, order.getOrderId());
        assertEquals("testuser", order.getUsername());

        order.setUsername("newuser");
        sut.save(order);
        Order updatedOrder = sut.findById(1001).orElseThrow();
        assertEquals("newuser", updatedOrder.getUsername());
    }

    @Test
    void testDelete() {
        assertEquals(0, sut.findAll().size());

        order.initOrder(account, cart);
        order.setOrderId(sequenceDao.getNextId("ordernum"));
        order.setNeedsInsert();
        sut.save(order);

        assertEquals(1, sut.findAll().size());
        Order order = sut.findById(1001).orElseThrow();

        sut.delete(order);
        assertEquals(0, sut.findAll().size());
    }

    @Test
    void testDeleteById() {
        assertEquals(0, sut.findAll().size());

        order.initOrder(account, cart);
        order.setOrderId(sequenceDao.getNextId("ordernum"));
        order.setNeedsInsert();
        sut.save(order);

        assertEquals(1, sut.findAll().size());
        Order order = sut.findById(1001).orElseThrow();

        sut.deleteById(order.getId());
        assertEquals(0, sut.findAll().size());
    }
}
