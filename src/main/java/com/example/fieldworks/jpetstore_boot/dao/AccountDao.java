package com.example.fieldworks.jpetstore_boot.dao;

import java.util.List;

import com.example.fieldworks.jpetstore_boot.domain.Account;
import org.springframework.dao.DataAccessException;

public interface AccountDao {

  Account getAccount(String username) throws DataAccessException;

  Account getAccount(String username, String password) throws DataAccessException;

  void insertAccount(Account account) throws DataAccessException;

  void updateAccount(Account account) throws DataAccessException;

	List getUsernameList() throws DataAccessException;

}
