/**
 * 
 */
package com.pranveraapp.core.web.api.endpoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

/**
 * @author elion
 *
 */
public abstract class BaseEndPoint implements ApplicationContextAware, MessageSourceAware {
	
	protected ApplicationContext context;
	
	protected  MessageSource messageSource;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.context = applicationContext;
	}


	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public MessageSource getMessageSource() {
		return messageSource;
	}
	
	public ApplicationContext getApplicationContext() {
		return context;
	}

}
