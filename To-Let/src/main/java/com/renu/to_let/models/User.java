package com.renu.to_let.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;


@Entity
public class User extends BaseUser<Long> {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
 
  private Long id;
  private String firstName;
  private String lastName;
  private String username;
  private String password;
  @Transient
  private String confirmPassword;
  private String country;
  private String detailAddress;
  @ManyToMany(cascade=CascadeType.ALL)
  @JoinTable(name = "user_role",
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

 public User() {
	// TODO Auto-generated constructor stub
}
 @Override
 public Long getId() {
	 
	 return id;
 }
 



public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public void setId(Long id) {
	this.id = id;
}


public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}







public String getConfirmPassword() {
	return confirmPassword;
}
public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getDetailAddress() {
	return detailAddress;
}
public void setDetailAddress(String detailAddress) {
	this.detailAddress = detailAddress;
}
public Set<Role> getRoles() {
	return roles;
}

public void setRoles(Set<Role> roles) {
	this.roles = roles;
}







  
}
