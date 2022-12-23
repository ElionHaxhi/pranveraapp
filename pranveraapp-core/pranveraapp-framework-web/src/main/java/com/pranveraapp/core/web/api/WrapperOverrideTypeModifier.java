/**
 * 
 */
package com.pranveraapp.core.web.api;

import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.type.TypeModifier;

/**
 * Provides an implementation of a {@link TypeModifier} that looks up types in the application context for wrapper overrides.
 * This allows for correct instantiation to occur when wrappers have been overridden and a client is actually sending JSON
 * @author elion
 *
 */
@ComponentScan("elWrapperOverrideTypeModifier")
public class WrapperOverrideTypeModifier extends TypeModifier implements ApplicationContextAware{
	
	private static final Log LOG = LogFactory.getLog(WrapperOverrideTypeModifier.class);
	
	protected ApplicationContext applicationContext;
	
	@Override
	public JavaType modifyType(JavaType type,Type jdkType,TypeBindings context, TypeFactory typeFactory){
		
		try{
			if(type.getClass().isAssignableFrom(SimpleType.class)){
				Object overriddenBean = applicationContext.getBean(type.getRawClass().getName());
				return SimpleType.construct(overriddenBean.getClass());
			}
		}
		catch(NoSuchBeanDefinitionException nsbe){
			LOG.debug("no configured bean for "+ type.getClass().getName() +" returning original type");
		}
		
		return type;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
}