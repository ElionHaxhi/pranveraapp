package com.pranveraapp.common.config.service;

import com.pranveraapp.common.config.RuntimeEnvironmentPropertiesManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that retrives property settig from the database.
 * If not set in the DB then return value from property files.
 * Created by elion on 03/02/16.
 */
@Service("elSystemPropertiesService")
public class SystemPropertiesServiceImpl implements SystemPropertiesService {


    @Autowired
    protected RuntimeEnvironmentPropertiesManager propMgr;

    @Override
    public int resolveIntSystemProperty(String name){
        String systemProperty = resolveSystemProperty(name,"0");
        return Integer.valueOf(systemProperty).intValue();
    }

    @Override
    public String resolveSystemProperty(String name, String defaultValue){
        String result =  resolveSystemProperty(name);
        if(StringUtils.isBlank(result)){
            return defaultValue;
        }
        return result;
    }

    @Override
    public String resolveSystemProperty(String name){
        /**
         * here is a piece of code that use extensionManager ,and ExtensionResultHolder
         * i'll implement it in future
         */

         String result;
         result = propMgr.getProperty(name);

        return result;

    }


}
