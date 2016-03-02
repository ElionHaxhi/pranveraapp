package com.pranveraapp.common.extensibility.jpa.copy;

import org.springframework.beans.factory.InitializingBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by elion on 24/01/16.
 */
public abstract class AbstractClassTransformer implements InitializingBean{

    protected static final Set<String> alreadyLoadedClasses = new HashSet<String>();
    protected List<String> preLoadClassNamePatterns = new ArrayList<String>();

    @Override
    public void afterPropertiesSet()throws Exception{

        if(preLoadClassNamePatterns !=null && !preLoadClassNamePatterns.isEmpty()){
            synchronized (alreadyLoadedClasses){
                for(String className : preLoadClassNamePatterns){
                    try{
                        if(!alreadyLoadedClasses.contains(className)){
                            Class.forName(className);
                            alreadyLoadedClasses.add(className);
                        }
                    }
                    catch (ClassNotFoundException cnfe){
                        throw new RuntimeException("Unable to force load class with name "+className+ " in the DirectCopyClassTransformes",cnfe );
                    }
                }
            }
        }
    }

    public void setPreLoadClassNamePatterns(List<String> fullyQualifiedClassNames){
        this.preLoadClassNamePatterns = fullyQualifiedClassNames;
    }
}
