package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Order;
import com.example.fieldworks.jpetstore_boot.domain.LineItem;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomOrderDaoImpl implements CustomOrderDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final JdbcAggregateTemplate jdbcAggregateTemplate;

    private final SequenceDao sequenceDao;

    public CustomOrderDaoImpl(NamedParameterJdbcTemplate jdbcTemplate, JdbcAggregateTemplate jdbcAggregateTemplate, SequenceDao sequenceDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcAggregateTemplate = jdbcAggregateTemplate;
        this.sequenceDao = sequenceDao;
    }

    @Override
    public List<Order> getOrdersByUsername(String username) throws DataAccessException {
        String sql = "SELECT * FROM orders WHERE userid = :username";
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(Order.class));
    }

    @Override
    public Order getOrder(int orderId) throws DataAccessException {
        String sql = "SELECT * FROM orders WHERE orderid = :orderId";
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        Order order = jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(Order.class));
        if (order != null) {
            String lineItemsSql = "SELECT * FROM lineitem WHERE orderid = :orderId";
            List<LineItem> lineItems = jdbcTemplate.query(lineItemsSql, params, new BeanPropertyRowMapper<>(LineItem.class));
            order.setLineItems(lineItems);
        }
        return order;
    }

    @Override
    public void insertOrder(Order order) throws DataAccessException {
        order.setOrderId(this.sequenceDao.getNextId("ordernum"));
        jdbcAggregateTemplate.insert(order);
        String sql = "INSERT INTO orderstatus (orderid, linenum, timestamp, status) VALUES (:orderId, :orderId, :orderDate, :status)";
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", order.getOrderId());
        params.put("orderDate", order.getOrderDate());
        params.put("status", order.getStatus());
        jdbcTemplate.update(sql, params);
        for (LineItem lineItem : order.getLineItems()) {
            lineItem.setOrderId(order.getOrderId());
            String lineItemSql = "INSERT INTO lineitem (orderid, linenum, itemid, quantity, unitprice) VALUES (:orderId, :lineNum, :itemId, :quantity, :unitPrice)";
            Map<String, Object> lineItemParams = new HashMap<>();
            lineItemParams.put("orderId", order.getOrderId());
            lineItemParams.put("lineNum", lineItem.getLineNumber());
            lineItemParams.put("itemId", lineItem.getItemId());
            lineItemParams.put("quantity", lineItem.getQuantity());
            lineItemParams.put("unitPrice", lineItem.getUnitPrice());
            jdbcTemplate.update(lineItemSql, lineItemParams);
        }
    }
}