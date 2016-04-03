/**
 * 
 */
package com.pranveraapp.core.web.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * 
 * @author elion
 *
 */
public class PranveraAppRestApiMvcConfiguration  extends WebMvcConfigurerAdapter {

	@Resource(name = "elWrapperOverrideTypeModifier")
	protected WrapperOverrideTypeModifier typeModifier;
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
		converters.add(getJsonConverter());
	}
	
	protected HttpMessageConverter<?> getJsonConverter(){
		return new MappingJackson2HttpMessageConverter(getJsonObjectMapper());
	}
	
	protected ObjectMapper getJsonObjectMapper(){
		
		Jackson2ObjectMapperBuilder builder = getObjectMapperBuilder();
		
		TypeFactory factory = TypeFactory.defaultInstance().withModifier(typeModifier);
		
		return builder.build().setTypeFactory(factory);
	}
	
	protected Jackson2ObjectMapperBuilder getObjectMapperBuilder(){
		return new Jackson2ObjectMapperBuilder()
		// Ensures JAXB get picked up
		.findModulesViaServiceLoader(true)
		// Enable/Disable some fetures
		.featuresToEnable(new Object[]{DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY})
		.featuresToDisable(new Object[]{SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED});
	}
}
















