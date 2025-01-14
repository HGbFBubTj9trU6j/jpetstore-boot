package com.example.fieldworks.jpetstore_boot.domain.logic;

import com.example.fieldworks.jpetstore_boot.domain.Order;

/**
 * Separate OrderService interface, implemented by PetStoreImpl
 * in addition to PetStoreFacade.
 *
 * <p>Mainly targeted at usage as remote service interface,
 * just exposing the <code>getOrder</code> method.
 *
 * @author Juergen Hoeller
 * @since 26.12.2003
 * @see PetStoreFacade
 * @see PetStoreImpl
 */
public interface OrderService {

	Order getOrder(int orderId);

}
