package com.example.fieldworks.jpetstore_boot.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

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
    void testInitOrder() {
        order.initOrder(account, cart);

        assertEquals("testuser", order.getUsername());
        assertNotNull(order.getOrderDate());
        assertEquals("John", order.getShipToFirstName());
        assertEquals("Doe", order.getShipToLastName());
        assertEquals("123 Main St", order.getShipAddress1());
        assertEquals("Apt 4B", order.getShipAddress2());
        assertEquals("Springfield", order.getShipCity());
        assertEquals("IL", order.getShipState());
        assertEquals("62701", order.getShipZip());
        assertEquals("USA", order.getShipCountry());
        assertEquals("John", order.getBillToFirstName());
        assertEquals("Doe", order.getBillToLastName());
        assertEquals("123 Main St", order.getBillAddress1());
        assertEquals("Apt 4B", order.getBillAddress2());
        assertEquals("Springfield", order.getBillCity());
        assertEquals("IL", order.getBillState());
        assertEquals("62701", order.getBillZip());
        assertEquals("USA", order.getBillCountry());
        assertEquals(30.0, order.getTotalPrice());
        assertEquals("999 9999 9999 9999", order.getCreditCard());
        assertEquals("12/03", order.getExpiryDate());
        assertEquals("Visa", order.getCardType());
        assertEquals("UPS", order.getCourier());
        assertEquals("CA", order.getLocale());
        assertEquals("P", order.getStatus());
        assertEquals(2, order.getLineItems().size());
    }

    @Test
    void testAddLineItem() {
        cartItem = new CartItem();
        cartItem.setItem(item2);
        cartItem.setQuantity(1);
        cartItem.setInStock(true);
        order.addLineItem(cartItem);

        assertEquals(1, order.getLineItems().size());
        LineItem lineItem = (LineItem) order.getLineItems().get(0);
        assertEquals("item2", lineItem.getItemId());
        assertEquals(1, lineItem.getQuantity());
        assertEquals(20.0, lineItem.getUnitPrice());
    }
}