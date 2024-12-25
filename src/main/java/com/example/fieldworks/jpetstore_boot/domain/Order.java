package com.example.fieldworks.jpetstore_boot.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Table("ORDERS")
public class Order implements Serializable, Persistable<Integer> {

  /* Private Fields */

  @Id
  @Column("ORDERID")
  private int orderId;
  @Column("USERID")
  private String username;
  @Column("ORDERDATE")
  private Date orderDate;
  @Column("SHIPADDR1")
  private String shipAddress1;
  @Column("SHIPADDR2")
  private String shipAddress2;
  @Column("SHIPCITY")
  private String shipCity;
  @Column("SHIPSTATE")
  private String shipState;
  @Column("SHIPZIP")
  private String shipZip;
  @Column("SHIPCOUNTRY")
  private String shipCountry;
  @Column("BILLADDR1")
  private String billAddress1;
  @Column("BILLADDR2")
  private String billAddress2;
  @Column("BILLCITY")
  private String billCity;
  @Column("BILLSTATE")
  private String billState;
  @Column("BILLZIP")
  private String billZip;
  @Column("BILLCOUNTRY")
  private String billCountry;
  @Column("COURIER")
  private String courier;
  @Column("TOTALPRICE")
  private double totalPrice;
  @Column("BILLTOFIRSTNAME")
  private String billToFirstName;
  @Column("BILLTOLASTNAME")
  private String billToLastName;
  @Column("SHIPTOFIRSTNAME")
  private String shipToFirstName;
  @Column("SHIPTOLASTNAME")
  private String shipToLastName;
  @Column("CREDITCARD")
  private String creditCard;
  @Column("EXPRDATE")
  private String expiryDate;
  @Column("CARDTYPE")
  private String cardType;
  @Column("LOCALE")
  private String locale;
  @Transient
  private String status;
  @Transient
  private List<LineItem> lineItems = new ArrayList<>();
  @Transient
  private boolean needsInsert = false;

  /* JavaBeans Properties */

  public int getOrderId() { return orderId; }
  public void setOrderId(int orderId) { this.orderId = orderId; }

  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }

  public Date getOrderDate() { return orderDate; }
  public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

  public String getShipAddress1() { return shipAddress1; }
  public void setShipAddress1(String shipAddress1) { this.shipAddress1 = shipAddress1; }

  public String getShipAddress2() { return shipAddress2; }
  public void setShipAddress2(String shipAddress2) { this.shipAddress2 = shipAddress2; }

  public String getShipCity() { return shipCity; }
  public void setShipCity(String shipCity) { this.shipCity = shipCity; }

  public String getShipState() { return shipState; }
  public void setShipState(String shipState) { this.shipState = shipState; }

  public String getShipZip() { return shipZip; }
  public void setShipZip(String shipZip) { this.shipZip = shipZip; }

  public String getShipCountry() { return shipCountry; }
  public void setShipCountry(String shipCountry) { this.shipCountry = shipCountry; }

  public String getBillAddress1() { return billAddress1; }
  public void setBillAddress1(String billAddress1) { this.billAddress1 = billAddress1; }

  public String getBillAddress2() { return billAddress2; }
  public void setBillAddress2(String billAddress2) { this.billAddress2 = billAddress2; }

  public String getBillCity() { return billCity; }
  public void setBillCity(String billCity) { this.billCity = billCity; }

  public String getBillState() { return billState; }
  public void setBillState(String billState) { this.billState = billState; }

  public String getBillZip() { return billZip; }
  public void setBillZip(String billZip) { this.billZip = billZip; }

  public String getBillCountry() { return billCountry; }
  public void setBillCountry(String billCountry) { this.billCountry = billCountry; }

  public String getCourier() { return courier; }
  public void setCourier(String courier) { this.courier = courier; }

  public double getTotalPrice() { return totalPrice; }
  public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

  public String getBillToFirstName() { return billToFirstName; }
  public void setBillToFirstName(String billToFirstName) { this.billToFirstName = billToFirstName; }

  public String getBillToLastName() { return billToLastName; }
  public void setBillToLastName(String billToLastName) { this.billToLastName = billToLastName; }

  public String getShipToFirstName() { return shipToFirstName; }
  public void setShipToFirstName(String shipFoFirstName) { this.shipToFirstName = shipFoFirstName; }

  public String getShipToLastName() { return shipToLastName; }
  public void setShipToLastName(String shipToLastName) { this.shipToLastName = shipToLastName; }

  public String getCreditCard() { return creditCard; }
  public void setCreditCard(String creditCard) { this.creditCard = creditCard; }

  public String getExpiryDate() { return expiryDate; }
  public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

  public String getCardType() { return cardType; }
  public void setCardType(String cardType) { this.cardType = cardType; }

  public String getLocale() { return locale; }
  public void setLocale(String locale) { this.locale = locale; }

  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }

  public void setLineItems(List<LineItem> lineItems) { this.lineItems = lineItems; }
  public List<LineItem> getLineItems() { return lineItems; }

  public void setNeedsInsert() { this.needsInsert = true; }

  /* Public Methods */

  public void initOrder(Account account, Cart cart) {
    username = account.getUsername();
    orderDate = new Date();

    shipToFirstName = account.getFirstName();
    shipToLastName = account.getLastName();
    shipAddress1 = account.getAddress1();
    shipAddress2 = account.getAddress2();
    shipCity = account.getCity();
    shipState = account.getState();
    shipZip = account.getZip();
    shipCountry = account.getCountry();

    billToFirstName = account.getFirstName();
    billToLastName = account.getLastName();
    billAddress1 = account.getAddress1();
    billAddress2 = account.getAddress2();
    billCity = account.getCity();
    billState = account.getState();
    billZip = account.getZip();
    billCountry = account.getCountry();

    totalPrice = cart.getSubTotal();

    creditCard = "999 9999 9999 9999";
    expiryDate = "12/03";
    cardType = "Visa";
    courier = "UPS";
    locale = "CA";
    status = "P";

    Iterator<CartItem> i = cart.getAllCartItems();
    while (i.hasNext()) {
      CartItem cartItem = i.next();
      addLineItem(cartItem);
    }
  }

  public void addLineItem(CartItem cartItem) {
    LineItem lineItem = new LineItem(lineItems.size() + 1, cartItem);
    addLineItem(lineItem);
  }

  public void addLineItem(LineItem lineItem) {
    lineItems.add(lineItem);
  }


  @Override
  public Integer getId() {
    return getOrderId();
  }

  @Override
  public boolean isNew() {
    return needsInsert;
  }
}
