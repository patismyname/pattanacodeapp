package com.pattanacode.utils;

import java.util.UUID;

public class RequestCorrelation {
 
    public static final String CORRELATION_ID = "correlationId";
 
    private static final ThreadLocal<String> id = new ThreadLocal<String>();
 
 
    public static String getId() { return id.get(); }
 
    public static void setId(String correlationId) { id.set(correlationId); }
    
    public static String getCorrelationId() {
    	return  UUID.randomUUID().toString();
    	
    }
}
