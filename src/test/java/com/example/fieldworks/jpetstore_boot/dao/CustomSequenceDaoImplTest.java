package com.example.fieldworks.jpetstore_boot.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataRetrievalFailureException;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(CustomSequenceDaoImpl.class)
class CustomSequenceDaoImplTest {

    @Autowired
    private CustomSequenceDao customSequenceDao;

    @Test
    void testGetNextId() {
        // Retrieve the next ID
        int nextId = customSequenceDao.getNextId("ordernum");
        assertEquals(1001, nextId);

        // Retrieve the next ID again
        nextId = customSequenceDao.getNextId("ordernum");
        assertEquals(1002, nextId);
    }

    @Test
    void testGetNextIdSequenceNotFound() {
        assertThrows(DataRetrievalFailureException.class, () -> customSequenceDao.getNextId("nonExistentSequence"));
    }
}