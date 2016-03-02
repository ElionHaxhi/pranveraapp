package com.pranveraapp.common;

import com.pranveraapp.common.web.PranveraAppRequestContext;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by elion on 03/02/16.
 */
public class RequestDTOImpl implements RequestDTO,Serializable {

    private String requestURI;

    private String fullUrlQueryString;

    private Boolean secure;

    public RequestDTOImpl(HttpServletRequest request){

        requestURI = request.getRequestURI();
        fullUrlQueryString = request.getRequestURL().toString();

        if(StringUtils.isNotEmpty(request.getQueryString())){
            fullUrlQueryString += "?" +request.getQueryString();
        }
        secure = ("HTTPS".equalsIgnoreCase(request.getScheme())|| request.isSecure());
    }

    @Override
    public String getRequestURI(){
        return requestURI;
    }

    @Override
    public String getFullUrlQueryString(){
        return fullUrlQueryString;
    }

    @Override
    public Boolean isSecure(){
        return secure;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public void setFullUrlQueryString(String fullUrlQueryString) {
        this.fullUrlQueryString = fullUrlQueryString;
    }

    public void setSecure(Boolean secure) {
        this.secure = secure;
    }

    public Map<String,Object> getProperties(){
        if(PranveraAppRequestContext.getPranveraAppRequestContext()!=null){
            return PranveraAppRequestContext.getPranveraAppRequestContext().getAdditionalProperties();
        }
        else{
            return null;
        }
    }
}































