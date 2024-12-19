package com.example.fieldworks.jpetstore_boot.domain.logic;

import com.example.fieldworks.jpetstore_boot.dao.*;
import com.example.fieldworks.jpetstore_boot.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetStoreImplTest {

    @Mock
    private AccountDao accountDao;

    @Mock
    private CategoryDao categoryDao;

    @Mock
    private ProductDao productDao;

    @Mock
    private ItemDao itemDao;

    @Mock
    private OrderDao orderDao;

    @InjectMocks
    private PetStoreImpl petStore;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAccount() {
        Account account = new Account();
        account.setUsername("testuser");
        when(accountDao.getAccount("testuser")).thenReturn(account);

        Account result = petStore.getAccount("testuser");
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
    }

    @Test
    void testInsertAccount() {
        Account account = new Account();
        petStore.insertAccount(account);
        verify(accountDao, times(1)).insertAccount(account);
    }

    @Test
    void testUpdateAccount() {
        Account account = new Account();
        petStore.updateAccount(account);
        verify(accountDao, times(1)).updateAccount(account);
    }

    @Test
    void testGetCategoryList() {
        List<Category> categories = Arrays.asList(new Category(), new Category());
        when(categoryDao.getCategoryList()).thenReturn(categories);

        List<Category> result = petStore.getCategoryList();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetProductListByCategory() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productDao.getProductListByCategory("CATS")).thenReturn(products);

        List<Product> result = petStore.getProductListByCategory("CATS");
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testInsertOrder() {
        Order order = new Order();
        petStore.insertOrder(order);
        verify(orderDao, times(1)).insertOrder(order);
        verify(itemDao, times(1)).updateQuantity(order);
    }

    @Test
    void testGetOrder() {
        Order order = new Order();
        when(orderDao.getOrder(1)).thenReturn(order);

        Order result = petStore.getOrder(1);
        assertNotNull(result);
    }

    @Test
    void testGetOrdersByUsername() {
        List<Order> orders = Arrays.asList(new Order(), new Order());
        when(orderDao.getOrdersByUsername("testuser")).thenReturn(orders);

        List<Order> result = petStore.getOrdersByUsername("testuser");
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}