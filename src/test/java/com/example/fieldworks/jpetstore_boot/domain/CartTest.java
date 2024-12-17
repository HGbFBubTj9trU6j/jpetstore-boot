package com.example.fieldworks.jpetstore_boot.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;
    private Item item1;
    private Item item2;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        item1 = new Item();
        item1.setItemId("item1");
        item1.setListPrice(10.0);

        item2 = new Item();
        item2.setItemId("item2");
        item2.setListPrice(20.0);
    }

    @Test
    void testAddItem() {
        cart.addItem(item1, true);
        assertEquals(1, cart.getNumberOfItems());

        cart.addItem(item1, true);
        assertEquals(1, cart.getNumberOfItems());

        cart.addItem(item2, true);
        assertEquals(2, cart.getNumberOfItems());
    }

    @Test
    void testRemoveItemById() {
        cart.addItem(item1, true);
        cart.addItem(item2, true);
        assertEquals(2, cart.getNumberOfItems());

        cart.removeItemById("item1");
        assertEquals(1, cart.getNumberOfItems());

        cart.removeItemById("item2");
        assertEquals(0, cart.getNumberOfItems());
    }

    @Test
    void testIncrementQuantityByItemId() {
        cart.addItem(item1, true);
        cart.incrementQuantityByItemId("item1");
        Iterator<CartItem> items = cart.getAllCartItems();
        assertTrue(items.hasNext());
        CartItem cartItem = items.next();
        assertEquals(2, cartItem.getQuantity());
    }

    @Test
    void testSetQuantityByItemId() {
        cart.addItem(item1, true);
        cart.setQuantityByItemId("item1", 5);
        Iterator<CartItem> items = cart.getAllCartItems();
        assertTrue(items.hasNext());
        CartItem cartItem = items.next();
        assertEquals(5, cartItem.getQuantity());
    }

    @Test
    void testGetSubTotal() {
        cart.addItem(item1, true);
        cart.addItem(item2, true);
        cart.setQuantityByItemId("item1", 2);
        cart.setQuantityByItemId("item2", 3);
        assertEquals(80.0, cart.getSubTotal());
    }
}