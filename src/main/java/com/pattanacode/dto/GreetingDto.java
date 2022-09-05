/**
 * 
 */
package com.pattanacode.dto;

import java.io.Serializable;

/**
 * @author patismyname
 *
 */
public class GreetingDto implements Serializable {
	
	private String sayGreeting;

	/**
	 * 
	 */
	public GreetingDto() {
		// TODO Auto-generated constructor stub
		sayGreeting="";
	}

	/**
	 * @return the sayGreeting
	 */
	public String getSayGreeting() {
		return sayGreeting;
	}

	/**
	 * @param sayGreeting the sayGreeting to set
	 */
	public void setSayGreeting(String sayGreeting) {
		this.sayGreeting = sayGreeting;
	}
	

}
