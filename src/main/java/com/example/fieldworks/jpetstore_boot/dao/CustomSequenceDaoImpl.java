package com.example.fieldworks.jpetstore_boot.dao;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomSequenceDaoImpl implements CustomSequenceDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CustomSequenceDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getNextId(String name) {
        String sql = "SELECT name, nextid FROM sequence WHERE name = :name";
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        Sequence sequence = jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(Sequence.class));
        if (sequence == null) {
            throw new DataRetrievalFailureException(
                    "Could not get next value of sequence '" + name + "': sequence does not exist");
        }
        sequence.setNextId(sequence.getNextId() + 1);
        String updateSql = "UPDATE sequence SET nextid = :nextId WHERE name = :name";
        params.put("nextId", sequence.getNextId());
        jdbcTemplate.update(updateSql, params);
        return sequence.getNextId();
    }
}