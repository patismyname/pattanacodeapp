/**
 * 
 */
package com.pattanacode.error;
import org.springframework.http.HttpStatus;

public class ManualException extends RuntimeException {

    private HttpStatus code;
    private ErrorMessage msg;
    private String reason;

    public ManualException(HttpStatus code, String message) {
        super(message);
        this.code = code;
        this.msg = new ErrorMessage(message);
        this.reason = message;
    }

    public String getReason() {
        return reason;
    }

    public HttpStatus getCode() {
        return code;
    }
    
    @Override
    public String getMessage() {
		return msg.toString();
	}
}
