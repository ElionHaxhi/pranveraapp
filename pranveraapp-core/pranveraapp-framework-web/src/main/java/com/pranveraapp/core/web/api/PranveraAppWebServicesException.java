/**
 * 
 */
package com.pranveraapp.core.web.api;

import java.util.HashMap;
import java.util.Map;

/**
 * @author elion
 *
 */
public class PranveraAppWebServicesException extends RuntimeException {

	public static final String AUTHOR_NOT_FOUND = "com.pranveraapp.core.web.api.PranveraAppWebServiceException.authornotfound";
	
	protected int httpStatusCode = 500;
	
	protected Map<String, Object[]> messages;

	PranveraAppWebServicesException(int httpStatusCode, Throwable cause){
		
		super(cause);
		this.httpStatusCode = httpStatusCode;
	}
	
	public static PranveraAppWebServicesException build(int httpStatusCode){
		return build(httpStatusCode);
	}
	
	public PranveraAppWebServicesException addMessage(String key, String message){
		getMessages().put(key,null);
		return this;
	}

	public Map<String, Object[]> getMessages() {
        if (this.messages == null) {
            this.messages = new HashMap<String,Object[]>();
        }
        return this.messages;
    }

	public PranveraAppWebServicesException addMessage(String key, Object param) {
        if (param != null) {
            return addMessage(key, new Object[] { param });
        } else {
            return addMessage(key, new Object[0]);
        }
    }
	
}
