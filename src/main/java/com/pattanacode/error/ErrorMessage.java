/**
 * 
 */
package com.pattanacode.error;

public class ErrorMessage {
	private String message;
	
	public ErrorMessage(String message)
	{
		this.message = message;
	}

	@Override
	public String toString() {
		return "{"
          		+ "\"error\": \""+this.message+"\""
          		+"}";
	}
}