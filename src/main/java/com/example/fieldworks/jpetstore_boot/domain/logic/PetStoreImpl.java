package com.example.fieldworks.jpetstore_boot.domain.logic;

import com.example.fieldworks.jpetstore_boot.domain.*;
import com.example.fieldworks.jpetstore_boot.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JPetStore primary business object.
 * 
 * <p>This object makes use of five DAO objects, decoupling it
 * from the details of working with persistence APIs. Thus
 * although this application uses iBATIS for data access,
 * another persistence tool could be dropped in without
 * breaking this class.
 *
 * <p>The DAOs are made available to the instance of this object
 * using Dependency Injection. (The DAOs are in turn configured
 * using Dependency Injection.) We use Setter Injection here,
 * exposing JavaBean setter methods for each DAO. This means there is
 * a JavaBean "property" for each DAO. In this case the properties
 * are write-only: there is no getter method to accompany the
 * setter methods. Getter methods are optional: implement them
 * only if you want to expose access to the properties in your
 * business object.
 *
 * <p>There is one instance of this class in the JPetStore application.
 * In Spring terminology, it is a "singleton". This means a
 * per-Application Context singleton. The factory creates a single
 * instance; there is no need for a private constructor, static
 * factory method etc as in the traditional implementation of
 * the Singleton Design Pattern. 
 *
 * <p>This is a POJO. It does not depend on any Spring APIs.
 * It's usable outside a Spring container, and can be instantiated
 * using new in a JUnit test. However, we can still apply declarative
 * transaction management to it using Spring AOP.
 *
 * <p>This class defines a default transaction attribute for all methods.
 * Note that this attribute definition is only necessary if using Commons
 * Attributes auto-proxying (see the "attributes" directory under the root of
 * JPetStore). No attributes are required with a TransactionFactoryProxyBean,
 * as in the default applicationContext.xml in the war/WEB-INF directory.
 *
 * <p>The following attribute definition uses Commons Attributes attribute syntax.
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute()
 *
 * @author Juergen Hoeller
 * @since 30.11.2003
 */
@Service
@Transactional
public class PetStoreImpl implements PetStoreFacade, OrderService {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private OrderDao orderDao;


	//-------------------------------------------------------------------------
	// Setter methods for dependency injection
	//-------------------------------------------------------------------------

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}


	//-------------------------------------------------------------------------
	// Operation methods, implementing the PetStoreFacade interface
	//-------------------------------------------------------------------------

	public Account getAccount(String username) {
		return this.accountDao.getAccount(username);
	}

	public Account getAccount(String username, String password) {
		return this.accountDao.getAccount(username, password);
	}

	public void insertAccount(Account account) {
		this.accountDao.insertAccount(account);
	}

	public void updateAccount(Account account) {
		this.accountDao.updateAccount(account);
	}

	public List<String> getUsernameList() {
		return this.accountDao.getUsernameList();
	}

	public List<Category> getCategoryList() {
		return this.categoryDao.getCategoryList();
	}

	public Category getCategory(String categoryId) {
		return this.categoryDao.getCategory(categoryId);
	}

	public List<Product> getProductListByCategory(String categoryId) {
		return this.productDao.getProductListByCategory(categoryId);
	}

	public List<Product> searchProductList(String keywords) {
		return this.productDao.searchProductList(keywords);
	}

	public Product getProduct(String productId) {
		return this.productDao.getProduct(productId);
	}

	public List<Item> getItemListByProduct(String productId) {
		return this.itemDao.getItemListByProduct(productId);
	}

	public Item getItem(String itemId) {
		return this.itemDao.getItem(itemId);
	}

	public boolean isItemInStock(String itemId) {
		return this.itemDao.isItemInStock(itemId);
	}

	public void insertOrder(Order order) {
		this.orderDao.insertOrder(order);
		this.itemDao.updateQuantity(order);
	}

	public Order getOrder(int orderId) {
		return this.orderDao.getOrder(orderId);
	}

	public List<Order> getOrdersByUsername(String username) {
		return this.orderDao.getOrdersByUsername(username);
	}

}
