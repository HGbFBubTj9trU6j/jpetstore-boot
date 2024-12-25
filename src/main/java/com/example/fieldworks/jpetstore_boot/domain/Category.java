package com.example.fieldworks.jpetstore_boot.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;


@Table("CATEGORY")
public class Category implements Serializable, Persistable<String> {

  /* Private Fields */

  @Id
  @Column("CATID")
  private String categoryId;
  @Column("NAME")
  private String name;
  @Column("DESCN")
  private String description;
  @Transient
  private boolean needsInsert = false;

  /* JavaBeans Properties */

  public String getCategoryId() { return categoryId; }
  public void setCategoryId(String categoryId) { this.categoryId = categoryId.trim(); }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  /* Public Methods */

  public String toString() {
    return getCategoryId();
  }

  public void setNeedsInsert() {
    this.needsInsert = true;
  }

  @Override
  public String getId() {
    return getCategoryId();
  }

  @Override
  public boolean isNew() {
    return needsInsert;
  }
}
