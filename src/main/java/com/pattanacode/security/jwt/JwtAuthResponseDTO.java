package com.pattanacode.security.jwt;


public class JwtAuthResponseDTO {

    private String tokenAccess;
    private String typeToken = "Bearer";

    public JwtAuthResponseDTO(String tokenAccess) {
        this.tokenAccess = tokenAccess;
    }

	/**
	 * @return the tokenAccess
	 */
	public String getTokenAccess() {
		return tokenAccess;
	}

	/**
	 * @param tokenAccess the tokenAccess to set
	 */
	public void setTokenAccess(String tokenAccess) {
		this.tokenAccess = tokenAccess;
	}

	/**
	 * @return the typeToken
	 */
	public String getTypeToken() {
		return typeToken;
	}

	/**
	 * @param typeToken the typeToken to set
	 */
	public void setTypeToken(String typeToken) {
		this.typeToken = typeToken;
	}
    
}
