package com.pattanacode.payload.response;

public class MessageResponse {
	
	private String username;
	private String message;


	public MessageResponse(String username, String message) {
	    this.username = username;
	    this.message = message;
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





	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
