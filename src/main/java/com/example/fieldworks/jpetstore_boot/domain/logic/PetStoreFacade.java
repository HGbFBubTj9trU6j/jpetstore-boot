package com.example.fieldworks.jpetstore_boot.domain.logic;

import com.example.fieldworks.jpetstore_boot.domain.*;

import java.util.List;

/**
 * JPetStore primary business interface.
 *
 * @author Juergen Hoeller
 * @since 30.11.2003
 */
public interface PetStoreFacade {

	Account getAccount(String username);

	Account getAccount(String username, String password);

	void insertAccount(Account account);

	void updateAccount(Account account);

	List getUsernameList();


	List getCategoryList();

	Category getCategory(String categoryId);
	

	List getProductListByCategory(String categoryId);

	List searchProductList(String keywords);

	Product getProduct(String productId);


	List getItemListByProduct(String productId);

	Item getItem(String itemId);

	boolean isItemInStock(String itemId);


	void insertOrder(Order order);

	Order getOrder(int orderId);

	List getOrdersByUsername(String username);

}
