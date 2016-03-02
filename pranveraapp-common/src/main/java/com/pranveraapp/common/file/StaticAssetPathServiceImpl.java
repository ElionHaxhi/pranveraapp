package com.pranveraapp.common.file;

import org.apache.velocity.tools.view.ImportSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by elion on 14/02/16.
 */
@Service("elStaticAssetPathService")
public class StaticAssetPathServiceImpl implements StaticAssetPathService {

    @Value("${asset.server.url.prefix.internal}")
    protected String staticAssetUrlPrefix;

    @Override
    public String convertAssetPath(String assetPath, String contextPath, boolean secureRequest){

        String returnValue = assetPath;

        if(returnValue != null && !ImportSupport.isAbsoluteUrl(returnValue)){

            if(!returnValue.startsWith("/")){
                returnValue = "/" + returnValue;
            }

            //Add context path
            if(contextPath !=null && ! contextPath.equals("")){
                if(!contextPath.equals("/")){
                    //Should not be the case, but let's handle it anyway
                    if(contextPath.endsWith("/")){
                        returnValue = returnValue.substring(1);
                    }
                    if(contextPath.startsWith("/")){
                        returnValue = contextPath + returnValue;
                    } else {
                        returnValue = "/" + contextPath + returnValue;
                    }
                }
            }
        }
        return returnValue;
    }
}
