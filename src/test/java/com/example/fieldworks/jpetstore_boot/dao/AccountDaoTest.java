package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJdbcTest
@ComponentScan(basePackages = "com.example.fieldworks.jpetstore_boot.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountDaoTest {
    @Autowired
    private AccountDao sut;

    @Test
    void testFindById() {
        Account account = sut.findById("j2ee").orElseThrow();
        assertEquals("j2ee", account.getUsername());
    }

    @Test
    void testFindAll() {
        assertEquals(2, sut.findAll().size());
    }

    @Test
    void testSaveAsInsert() {
        assertEquals(2, sut.findAll().size());
        Account account = new Account();
        account.setUsername("newuser");
        account.setPassword("newpassword");
        account.setEmail("email@example.com");
        account.setFirstName("newFirstName");
        account.setLastName("newLastName");
        account.setAddress1("newAddress1");
        account.setCity("newCity");
        account.setState("newState");
        account.setZip("newZip");
        account.setCountry("newCountry");
        account.setPhone("newPhone");
        account.setNeedsInsert();

        sut.save(account);

        assertEquals(3, sut.findAll().size());
    }

    @Test
    void testSaveAsUpdate() {
        assertEquals(2, sut.findAll().size());
        Account account = sut.findById("j2ee").orElseThrow();
        assertEquals("901 San Antonio Road", account.getAddress1());

        account.setAddress1("updatedAddress1");;
        sut.save(account);

        assertEquals(2, sut.findAll().size());
        Account insertedAccount = sut.findById("j2ee").orElseThrow();
        assertEquals("updatedAddress1", insertedAccount.getAddress1());
    }
    @Test
    void testDelete() {
        assertEquals(2, sut.findAll().size());
        Account account = sut.findById("j2ee").orElseThrow();
        assertEquals("901 San Antonio Road", account.getAddress1());

        sut.delete(account);

        assertEquals(1, sut.findAll().size());
    }

    @Test
    void testDeleteById() {
        assertEquals(2, sut.findAll().size());
        Account account = sut.findById("j2ee").orElseThrow();
        assertEquals("901 San Antonio Road", account.getAddress1());

        sut.deleteById(account.getId());

        assertEquals(1, sut.findAll().size());
    }
}
