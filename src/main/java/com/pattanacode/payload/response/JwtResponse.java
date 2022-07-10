package com.pattanacode.payload.response;

import org.springframework.http.HttpStatus;

import com.pattanacode.models.Role;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer ";
	private String userId;
	private String username;
	private String email;
	private int status;
	private  String message;
	private  int expires;
	private HttpStatus httpStatus;
	private List<Role> roles;

	
	public JwtResponse(HttpStatus httpStatus,String accessToken, String userId, String email, int status,String message,int expires,List<Role> roles) {
		this.httpStatus=httpStatus;
		this.token = accessToken;
		this.userId = userId;
		//this.username = username;
		this.email = email;
		this.status=status;
		this.message=message;
		this.expires=expires;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * @return the expires
	 */
	public int getExpires() {
		return expires;
	}

	/**
	 * @param expires the expires to set
	 */
	public void setExpires(int expires) {
		this.expires = expires;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	
	
}
