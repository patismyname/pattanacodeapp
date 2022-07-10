/**
 * 
 */
package com.pattanacode.payload.response;

/**
 * @author patismyname
 *
 */
public class ModelResponse {
	
	private JwtResponse data;

	/**
	 * 
	 */
	public ModelResponse(JwtResponse jwtResponse) {
		// TODO Auto-generated constructor stub
		this.data=jwtResponse;
	}

	/**
	 * @return the data
	 */
	public JwtResponse getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(JwtResponse data) {
		this.data = data;
	}

	
	
	

}
