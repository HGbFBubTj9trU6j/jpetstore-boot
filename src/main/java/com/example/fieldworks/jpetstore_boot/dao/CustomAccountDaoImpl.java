package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomAccountDaoImpl implements CustomAccountDao {

    private final JdbcTemplate jdbcTemplate;

    public CustomAccountDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getAccount(String username) {
        String sql = "SELECT signon.username as userid, account.email, account.firstname, account.lastname, account.status, account.addr1, account.addr2, account.city, account.state, account.zip, account.country, account.phone, profile.langpref, profile.favcategory, profile.mylistopt, profile.banneropt, bannerdata.bannername FROM account, profile, signon, bannerdata WHERE account.userid = ? AND signon.username = account.userid AND profile.userid = account.userid AND profile.favcategory = bannerdata.favcategory";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Account account = new Account();
            account.setUsername(rs.getString("userid"));
            account.setPassword(null); // Password is not selected in this query
            account.setEmail(rs.getString("email"));
            account.setFirstName(rs.getString("firstname"));
            account.setLastName(rs.getString("lastname"));
            account.setStatus(rs.getString("status"));
            account.setAddress1(rs.getString("addr1"));
            account.setAddress2(rs.getString("addr2"));
            account.setCity(rs.getString("city"));
            account.setState(rs.getString("state"));
            account.setZip(rs.getString("zip"));
            account.setCountry(rs.getString("country"));
            account.setPhone(rs.getString("phone"));
            account.setLanguagePreference(rs.getString("langpref"));
            account.setFavouriteCategoryId(rs.getString("favcategory"));
            account.setListOption(rs.getBoolean("mylistopt"));
            account.setBannerOption(rs.getBoolean("banneropt"));
            account.setBannerName(rs.getString("bannername"));
            return account;
        }, username);
    }

    @Override
    public Account getAccount(String username, String password) {
        String sql = "SELECT signon.username as userid, account.email, account.firstname, account.lastname, account.status, account.addr1, account.addr2, account.city, account.state, account.zip, account.country, account.phone, profile.langpref, profile.favcategory, profile.mylistopt, profile.banneropt, bannerdata.bannername FROM account, profile, signon, bannerdata WHERE account.userid = ? AND signon.password = ? AND signon.username = account.userid AND profile.userid = account.userid AND profile.favcategory = bannerdata.favcategory";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Account account = new Account();
            account.setUsername(rs.getString("userid"));
            account.setPassword(null); // Password is not selected in this query
            account.setEmail(rs.getString("email"));
            account.setFirstName(rs.getString("firstname"));
            account.setLastName(rs.getString("lastname"));
            account.setStatus(rs.getString("status"));
            account.setAddress1(rs.getString("addr1"));
            account.setAddress2(rs.getString("addr2"));
            account.setCity(rs.getString("city"));
            account.setState(rs.getString("state"));
            account.setZip(rs.getString("zip"));
            account.setCountry(rs.getString("country"));
            account.setPhone(rs.getString("phone"));
            account.setLanguagePreference(rs.getString("langpref"));
            account.setFavouriteCategoryId(rs.getString("favcategory"));
            account.setListOption(rs.getBoolean("mylistopt"));
            account.setBannerOption(rs.getBoolean("banneropt"));
            account.setBannerName(rs.getString("bannername"));
            return account;
        }, username, password);
    }

    @Override
    public void insertAccount(Account account) {
        String sqlAccount = "INSERT INTO account (email, firstname, lastname, status, addr1, addr2, city, state, zip, country, phone, userid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlAccount, account.getEmail(), account.getFirstName(), account.getLastName(), account.getStatus(), account.getAddress1(), account.getAddress2(), account.getCity(), account.getState(), account.getZip(), account.getCountry(), account.getPhone(), account.getUsername());

        String sqlProfile = "INSERT INTO profile (langpref, favcategory, mylistopt, banneropt, userid) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlProfile, account.getLanguagePreference(), account.getFavouriteCategoryId(), account.isListOption(), account.isBannerOption(), account.getUsername());

        String sqlSignon = "INSERT INTO signon (password, username) VALUES (?, ?)";
        jdbcTemplate.update(sqlSignon, account.getPassword(), account.getUsername());
    }

    @Override
    public void updateAccount(Account account) {
        String sqlAccount = "UPDATE account SET email = ?, firstname = ?, lastname = ?, status = ?, addr1 = ?, addr2 = ?, city = ?, state = ?, zip = ?, country = ?, phone = ? WHERE userid = ?";
        jdbcTemplate.update(sqlAccount, account.getEmail(), account.getFirstName(), account.getLastName(), account.getStatus(), account.getAddress1(), account.getAddress2(), account.getCity(), account.getState(), account.getZip(), account.getCountry(), account.getPhone(), account.getUsername());

        String sqlProfile = "UPDATE profile SET langpref = ?, favcategory = ?, mylistopt = ?, banneropt = ? WHERE userid = ?";
        jdbcTemplate.update(sqlProfile, account.getLanguagePreference(), account.getFavouriteCategoryId(), account.isListOption(), account.isBannerOption(), account.getUsername());

        if (account.getPassword() != null && !account.getPassword().isEmpty()) {
            String sqlSignon = "UPDATE signon SET password = ? WHERE username = ?";
            jdbcTemplate.update(sqlSignon, account.getPassword(), account.getUsername());
        }
    }

    @Override
    public List<String> getUsernameList() {
        String sql = "SELECT username FROM signon";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("username"));
    }
}