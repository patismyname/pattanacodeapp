package com.pattanacode.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "users")
public class User {

	
  @Id
  private String userId;

  //@NotBlank
  @Size(max = 50)
  private String username;

  @NotBlank
  @Size(max = 250)
  @Email
  private String email;

  @NotBlank
  @Size(max = 250)
  private String password;
  
  private String pincode;
  
  @NotBlank
  private String passwordType;// P used is Password, C used is PinCode
  
  
  private Date startDate;
  
  private Date expiredDate;
  
 
  @DBRef
  private Set<Role> roles = new HashSet<>();
  
  
 // @NotBlank
  @Size(max = 1)
  private String flagStatus;
  
 // @NotBlank
  @Size(max = 10)
  private String createdBy;
  

  //@DateTimeFormat(style = "M-") 
  @CreatedDate
  private Date createdDate;
  



  public User(String username, 
		  String email, 
		  String password,
		  String pincode,
		  String passwordType,
		  Date startDate,
		  Date expiredDate,
		  String flagStatus,
		  Date createdDate,
		  String createdBy
		   ) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.pincode=pincode;
    this.passwordType=passwordType;
    this.startDate=startDate;
    this.expiredDate=expiredDate;
    this.flagStatus=flagStatus;
    this.createdDate=createdDate;
    this.createdBy=createdBy;
    
  }



  public User(
		  ) {
	  
	// TODO Auto-generated constructor stub
}



/**
 * @return the userId
 */
public String getUserId() {
	return userId;
}

/**
 * @param userId the userId to set
 */
public void setUserId(String userId) {
	this.userId = userId;
}



/**
 * @return the username
 */
public String getUsername() {
	return username;
}



/**
 * @param username the username to set
 */
public void setUsername(String username) {
	this.username = username;
}



/**
 * @return the email
 */
public String getEmail() {
	return email;
}



/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}



/**
 * @return the password
 */
public String getPassword() {
	return password;
}



/**
 * @param password the password to set
 */
public void setPassword(String password) {
	this.password = password;
}



/**
 * @return the pincode
 */
public String getPincode() {
	return pincode;
}



/**
 * @param pincode the pincode to set
 */
public void setPincode(String pincode) {
	this.pincode = pincode;
}



/**
 * @return the passwordType
 */
public String getPasswordType() {
	return passwordType;
}



/**
 * @param passwordType the passwordType to set
 */
public void setPasswordType(String passwordType) {
	if(passwordType!=null)
	this.passwordType = passwordType;
	else {
		this.passwordType="P";
	}
}



/**
 * @return the startDate
 */
public Date getStartDate() {
	return startDate;
}

/**
 * @param startDate the startDate to set
 */
public void setStartDate(Date startDate) {
	this.startDate = startDate;
}

/**
 * @return the expiredDate
 */
public Date getExpiredDate() {
	return expiredDate;
}

/**
 * @param expiredDate the expiredDate to set
 */
public void setExpiredDate(Date expiredDate) {
	this.expiredDate = expiredDate;
}

/**
 * @return the createdDate
 */
public Date getCreatedDate() {
	return createdDate;
}

/**
 * @param createdDate the createdDate to set
 */
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}


/**
 * @return the flagStatus
 */
public String getFlagStatus() {
	return flagStatus;
}

/**
 * @param flagStatus the flagStatus to set
 */
public void setFlagStatus(String flagStatus) {
	this.flagStatus = flagStatus;
}

/**
 * @return the createdBy
 */
public String getCreatedBy() {
	return createdBy;
}

/**
 * @param createdBy the createdBy to set
 */
public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}

public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
