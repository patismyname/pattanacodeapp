package com.pattanacode.payload.response;

public class MessageResponse {
	
	private String transactionId;
	private String username;
	private String message;


	public MessageResponse(String transactionId, String username, String message) {
		this.transactionId=transactionId;
	    this.username = username;
	    this.message = message;
	  }

	
	


	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}





	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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
