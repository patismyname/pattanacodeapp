/**
 * 
 */
package com.pattanacode.models;

/**
 * @author patismyname
 *
 */
public class JSONResult {

	private int statusCode;
	private String information;
	private String data;
	
	/**
	 * 
	 */
	public JSONResult() {
		// TODO Auto-generated constructor stub
	}
	
	public JSONResult (int statusCode, String information, String data) {
		this.statusCode=statusCode;
		this.information=information;
		this.data=data;
		
	}

}
