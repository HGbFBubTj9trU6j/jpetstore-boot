package com.example.fieldworks.jpetstore_boot.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    private CartItem cartItem;
    private Item item;

    @BeforeEach
    void setUp() {
        cartItem = new CartItem();
        item = new Item();
        item.setListPrice(10.0);
        cartItem.setItem(item);
        cartItem.setQuantity(2);
        cartItem.setInStock(true);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(item, cartItem.getItem());
        assertEquals(2, cartItem.getQuantity());
        assertTrue(cartItem.isInStock());

        Item newItem = new Item();
        newItem.setListPrice(20.0);
        cartItem.setItem(newItem);
        cartItem.setQuantity(3);
        cartItem.setInStock(false);

        assertEquals(newItem, cartItem.getItem());
        assertEquals(3, cartItem.getQuantity());
        assertFalse(cartItem.isInStock());
    }

    @Test
    void testIncrementQuantity() {
        cartItem.incrementQuantity();
        assertEquals(3, cartItem.getQuantity());
    }

    @Test
    void testGetTotalPrice() {
        assertEquals(20.0, cartItem.getTotalPrice());

        cartItem.setQuantity(3);
        assertEquals(30.0, cartItem.getTotalPrice());
    }
}