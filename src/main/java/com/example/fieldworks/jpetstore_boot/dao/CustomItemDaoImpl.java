package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Item;
import com.example.fieldworks.jpetstore_boot.domain.Order;
import com.example.fieldworks.jpetstore_boot.domain.LineItem;
import com.example.fieldworks.jpetstore_boot.domain.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomItemDaoImpl implements CustomItemDao {

    private final JdbcTemplate jdbcTemplate;

    public CustomItemDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void updateQuantity(Order order) throws DataAccessException {
        String sql = "UPDATE inventory SET qty = qty - ? WHERE itemid = ?";
        for (LineItem lineItem : order.getLineItems()) {
            jdbcTemplate.update(sql, lineItem.getQuantity(), lineItem.getItemId());
        }
    }

    @Override
    public boolean isItemInStock(String itemId) throws DataAccessException {
        String sql = "SELECT qty FROM inventory WHERE itemid = ?";
        Integer quantity = jdbcTemplate.queryForObject(sql, Integer.class, itemId);
        return quantity != null && quantity > 0;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) throws DataAccessException {
        String sql = "SELECT itemid, listprice, unitcost, supplier, i.productid, name, descn, category, status, attr1, attr2, attr3, attr4, attr5 FROM item i, product p WHERE p.productid = i.productid AND i.productid = ?";
        List<Item> items = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Item item = new Item();
            item.setItemId(rs.getString("itemid"));
            item.setListPrice(rs.getDouble("listprice"));
            item.setUnitCost(rs.getDouble("unitcost"));
            item.setSupplierId(rs.getInt("supplier"));
            item.setProductId(rs.getString("productid"));
            Product product = new Product();
            product.setProductId(rs.getString("productid"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("descn"));
            product.setCategoryId(rs.getString("category"));
            item.setProduct(product);
            item.setStatus(rs.getString("status"));
            item.setAttribute1(rs.getString("attr1"));
            item.setAttribute2(rs.getString("attr2"));
            item.setAttribute3(rs.getString("attr3"));
            item.setAttribute4(rs.getString("attr4"));
            item.setAttribute5(rs.getString("attr5"));
            return item;
        }, productId);
        if (items.isEmpty()) {
            throw new EmptyResultDataAccessException("No items found", 1);
        }
        return items;

    }

    @Override
    public Item getItem(String itemId) throws DataAccessException {
        String sql = "SELECT i.itemid, listprice, unitcost, supplier, i.productid, name, descn, category, status, attr1, attr2, attr3, attr4, attr5, qty FROM item i, inventory v, product p WHERE p.productid = i.productid AND i.itemid = v.itemid AND i.itemid = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Item i = new Item();
            i.setItemId(rs.getString("itemid"));
            i.setListPrice(rs.getDouble("listprice"));
            i.setUnitCost(rs.getDouble("unitcost"));
            i.setSupplierId(rs.getInt("supplier"));
            i.setProductId(rs.getString("productid"));
            Product p = new Product();
            p.setProductId(rs.getString("productid"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("descn"));
            p.setCategoryId(rs.getString("category"));
            i.setStatus(rs.getString("status"));
            i.setAttribute1(rs.getString("attr1"));
            i.setAttribute2(rs.getString("attr2"));
            i.setAttribute3(rs.getString("attr3"));
            i.setAttribute4(rs.getString("attr4"));
            i.setAttribute5(rs.getString("attr5"));
            i.setQuantity(rs.getInt("qty"));
            return i;
        }, itemId);
    }
}