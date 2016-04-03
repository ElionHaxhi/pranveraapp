/**
 * 
 */
package com.pranveraapp.core.web.api.wrapper;

import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author elion
 *
 */
public abstract class BaseWrapper implements ApplicationContextAware {

	@XmlTransient
	protected ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
}
