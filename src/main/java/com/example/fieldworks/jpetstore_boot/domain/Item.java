package com.example.fieldworks.jpetstore_boot.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;


@Table("ITEM")
public class Item implements Serializable, Persistable<String> {

  /* Private Fields */

  @Id
  @Column("ITEMID")
  private String itemId;
  @Column("PRODUCTID")
  private String productId;
  @Column("LISTPRICE")
  private double listPrice;
  @Column("UNITCOST")
  private double unitCost;
  @Column("SUPPLIER")
  private int supplierId;
  @Column("STATUS")
  private String status;
  @Column("ATTR1")
  private String attribute1;
  @Column("ATTR2")
  private String attribute2;
  @Column("ATTR3")
  private String attribute3;
  @Column("ATTR4")
  private String attribute4;
  @Column("ATTR5")
  private String attribute5;
  @Transient
  private Product product;
  @Transient
  private int quantity;
  @Transient
  private boolean needsInsert = false;

  /* JavaBeans Properties */

  public String getItemId() { return itemId; }
  public void setItemId(String itemId) { this.itemId = itemId.trim(); }

  public int getQuantity() { return quantity; }
  public void setQuantity(int quantity) { this.quantity = quantity; }

  public Product getProduct() { return product; }
  public void setProduct(Product product) { this.product = product; }

  public String getProductId() { return productId; }
  public void setProductId(String productId) { this.productId = productId; }

  public int getSupplierId() { return supplierId; }
  public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

  public double getListPrice() { return listPrice; }
  public void setListPrice(double listPrice) { this.listPrice = listPrice; }

  public double getUnitCost() { return unitCost; }
  public void setUnitCost(double unitCost) { this.unitCost = unitCost; }

  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }

  public String getAttribute1() { return attribute1; }
  public void setAttribute1(String attribute1) { this.attribute1 = attribute1; }

  public String getAttribute2() { return attribute2; }
  public void setAttribute2(String attribute2) { this.attribute2 = attribute2; }

  public String getAttribute3() { return attribute3; }
  public void setAttribute3(String attribute3) { this.attribute3 = attribute3; }

  public String getAttribute4() { return attribute4; }
  public void setAttribute4(String attribute4) { this.attribute4 = attribute4; }

  public String getAttribute5() { return attribute5; }
  public void setAttribute5(String attribute5) { this.attribute5 = attribute5; }

  /* Public Methods */

  public String toString() {
    return "(" + getItemId().trim() + "-" + getProductId().trim() + ")";
  }

  public void setNeedsInsert() {
    this.needsInsert = true;
  }

  @Override
  public String getId() {
    return getItemId();
  }

  @Override
  public boolean isNew() {
    return needsInsert;
  }
}
