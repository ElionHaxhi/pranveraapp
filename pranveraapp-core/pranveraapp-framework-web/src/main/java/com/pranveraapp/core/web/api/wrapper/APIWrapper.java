/**
 * 
 */
package com.pranveraapp.core.web.api.wrapper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author elion
 *
 */
public interface APIWrapper<T> {
	
	public void wrapDetails(T model, HttpServletRequest request);
	
	public void wrapSummary(T model, HttpServletRequest request);
	
}
