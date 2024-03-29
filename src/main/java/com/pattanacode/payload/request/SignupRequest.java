package com.pattanacode.payload.request;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.*;

import com.pattanacode.models.ERole;
import com.pattanacode.models.Role;
import com.pattanacode.utils.DateTimeUtils;
 
public class SignupRequest {
	
    private String id;
	
   // @NotBlank
    @Size(min = 8, max = 50)
    private String username;
 
    @NotBlank
    @Size(max = 250)
    @Email
    private String email;
    
    private Set<String> roles;
    
    @NotBlank
    @Size(min = 8, max = 250)
    private String password;
    
    private String pinCode;
    
    private String passwordType;
    
    
    //@NotBlank
    @Size(max = 250)
    private String firstName;
    
   
    @Size(max = 250)
    private String middleName;
    
   // @NotBlank
    @Size(max = 250)
    private String lastName;
    
    @Size(max = 250)
    private String nickName;
    
   
    @Size(max = 3)
    private String countryCode;// Thailand is 066
    
   // @NotBlank
    @Size(max = 10)
    private String mobileNumber;
    
    private Date startDate;
    
    private Date expiredDate;
    
    private Date birthDate;
    
    private String customerType;// S is Student
                                // T is Teacher
                                // C is Customer
    
    private String flagStatus;
  
    /**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    /**
	 * @return the pinCode
	 */
	public String getPinCode() {
		return pinCode;
	}

	/**
	 * @param pinCode the pinCode to set
	 */
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
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
		this.passwordType = passwordType;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		if(countryCode!=null)
		  this.countryCode = countryCode;
		else {
			this.countryCode="066";
		}
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Set<String> getRoles() {
      return this.roles;
    }
    
    public void setRole(Set<String> roles) {
    	
      Set<String> set = Set.<String>of("user");
      
      if(roles!=null)
       this.roles = roles;
      else {
    	  this.roles =set;
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
		if(startDate!=null)
		this.startDate = startDate;
		else {
			this.startDate=DateTimeUtils.getSystemDate();
		}
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
		if(expiredDate!=null)
		this.expiredDate = expiredDate;
		else {
			this.expiredDate=DateTimeUtils.getExpirationDate();
		}
	}
	
	

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	/**
	 * @return the flagStatus
	 */
	public String getFlagStatus() {
		return flagStatus;
	}

	
	/**
	 * @return the customerType
	 */
	public String getCustomerType() {
		return customerType;
	}

	/**
	 * @param customerType the customerType to set
	 */
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	/**
	 * @param flagStatus the flagStatus to set
	 */
	public void setFlagStatus(String flagStatus) {
		if(flagStatus!=null)
		this.flagStatus = flagStatus;
		else {
			this.flagStatus="A";
		}
	}
    
	
    
}
