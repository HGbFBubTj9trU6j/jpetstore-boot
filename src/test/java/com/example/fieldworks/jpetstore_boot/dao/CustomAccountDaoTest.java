package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@ComponentScan(basePackages = "com.example.fieldworks.jpetstore_boot.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomAccountDaoTest {

    @Autowired
    @Qualifier("customAccountDaoImpl")
    private CustomAccountDao customAccountDao;

    @Test
    void testGetAccountByUsername() {
        Account account = customAccountDao.getAccount("j2ee");
        assertNotNull(account);
        assertEquals("j2ee", account.getUsername());
    }

    @Test
    void testGetAccountByUsernameNotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            customAccountDao.getAccount("notfound");
        });
    }
    @Test
    void testGetAccountByUsernameAndPassword() {
        Account account = customAccountDao.getAccount("j2ee", "j2ee");
        assertNotNull(account);
        assertEquals("j2ee", account.getUsername());
    }
    @Test
    void testGetAccountByUsernameAndPasswordNotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            customAccountDao.getAccount("j2ee", "notfound");
        });
    }
    @Test
    void testInsertAccount() {
        Account account = new Account();
        account.setUsername("newuser");
        account.setPassword("newpassword");
        account.setEmail("newuser@example.com");
        account.setFirstName("New");
        account.setLastName("User");
        account.setStatus("OK");
        account.setAddress1("123 New User St");
        account.setCity("New User City");
        account.setState("NU");
        account.setZip("12345");
        account.setCountry("USA");
        account.setPhone("123-456-7890");
        account.setLanguagePreference("english");
        account.setFavouriteCategoryId("FISH");
        account.setListOption(true);
        account.setBannerOption(true);
        account.setBannerName("bannerName");

        customAccountDao.insertAccount(account);

        Account insertedAccount = customAccountDao.getAccount("newuser");
        assertNotNull(insertedAccount);
        assertEquals("newuser", insertedAccount.getUsername());
    }

    @Test
    void testUpdateAccount() {
        Account account = customAccountDao.getAccount("j2ee", "j2ee");
        account.setPassword("j2eee");
        assertNotNull(account);

        account.setEmail("updatedemail@example.com");
        customAccountDao.updateAccount(account);

        Account updatedAccount = customAccountDao.getAccount("j2ee");
        assertNotNull(updatedAccount);
        assertEquals("updatedemail@example.com", updatedAccount.getEmail());
    }

    @Test
    void testGetUsernameList() {
        List<String> usernameList = customAccountDao.getUsernameList();
        assertNotNull(usernameList);
        assertEquals(2, usernameList.size());
    }

}