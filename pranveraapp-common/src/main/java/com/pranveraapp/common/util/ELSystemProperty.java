package com.pranveraapp.common.util;

import com.pranveraapp.common.config.service.SystemPropertiesService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Convenience class to facilitate getting system properties
 * Note that this class is scaned as a bean to pick up the applicationContext, but the methods
 * this class provide should be invoked statically
 * Created by elion on 03/02/16.
 */
@Service("elELSystemProperty")
public class ELSystemProperty implements ApplicationContextAware{

    protected static ApplicationContext applicationContext;

    public static int resolveIntSystemProperty(String name){
        return getSystemPropertiesService().resolveIntSystemProperty(name);
    }



    protected static SystemPropertiesService getSystemPropertiesService(){
        return (SystemPropertiesService) applicationContext.getBean("elSystemPropertiesService");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
        ELSystemProperty.applicationContext = applicationContext;
    }

}
