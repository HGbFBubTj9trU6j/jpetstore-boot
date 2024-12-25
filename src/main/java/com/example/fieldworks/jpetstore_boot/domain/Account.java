package com.example.fieldworks.jpetstore_boot.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;


@Table("ACCOUNT")
public class Account implements Serializable, Persistable<String> {

  /* Private Fields */

  @Id
  @Column("USERID")
  private String username;
  @Transient
  private String password;
  @Column("EMAIL")
  private String email;
  @Column("FIRSTNAME")
  private String firstName;
  @Column("LASTNAME")
  private String lastName;
  @Column("STATUS")
  private String status;
  @Column("ADDR1")
  private String address1;
  @Column("ADDR2")
  private String address2;
  @Column("CITY")
  private String city;
  @Column("STATE")
  private String state;
  @Column("ZIP")
  private String zip;
  @Column("COUNTRY")
  private String country;
  @Column("PHONE")
  private String phone;
  @Transient
  private String favouriteCategoryId;
  @Transient
  private String languagePreference;
  @Transient
  private boolean listOption;
  @Transient
  private boolean bannerOption;
  @Transient
  private String bannerName;
  @Transient
  private boolean needsInsert = false;

  /* JavaBeans Properties */

  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }

  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }

  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }

  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }

  public String getAddress1() { return address1; }
  public void setAddress1(String address1) { this.address1 = address1; }

  public String getAddress2() { return address2; }
  public void setAddress2(String address2) { this.address2 = address2; }

  public String getCity() { return city; }
  public void setCity(String city) { this.city = city; }

  public String getState() { return state; }
  public void setState(String state) { this.state = state; }

  public String getZip() { return zip; }
  public void setZip(String zip) { this.zip = zip; }

  public String getCountry() { return country; }
  public void setCountry(String country) { this.country = country; }

  public String getPhone() { return phone; }
  public void setPhone(String phone) { this.phone = phone; }

  public String getFavouriteCategoryId() { return favouriteCategoryId; }
  public void setFavouriteCategoryId(String favouriteCategoryId) { this.favouriteCategoryId = favouriteCategoryId; }

  public String getLanguagePreference() { return languagePreference; }
  public void setLanguagePreference(String languagePreference) { this.languagePreference = languagePreference; }

  public boolean isListOption() { return listOption; }
  public void setListOption(boolean listOption) { this.listOption = listOption; }
	public int getListOptionAsInt() { return listOption ? 1 : 0; }

  public boolean isBannerOption() { return bannerOption; }
  public void setBannerOption(boolean bannerOption) { this.bannerOption = bannerOption; }
	public int getBannerOptionAsInt() { return bannerOption ? 1 : 0; }

  public String getBannerName() { return bannerName; }
  public void setBannerName(String bannerName) { this.bannerName = bannerName; }

  public void setNeedsInsert() { this.needsInsert = true; }

  @Override
  public String getId() {
    return getUsername();
  }

  @Override
  public boolean isNew() {
    return needsInsert;
  }
}
