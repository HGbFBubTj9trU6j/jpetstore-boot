package com.example.fieldworks.jpetstore_boot.dao;

import com.example.fieldworks.jpetstore_boot.domain.Account;

import java.util.List;

public interface CustomAccountDao {

  Account getAccount(String username);

  Account getAccount(String username, String password);

  void insertAccount(Account account);

  void updateAccount(Account account);

	List<String> getUsernameList();

}
