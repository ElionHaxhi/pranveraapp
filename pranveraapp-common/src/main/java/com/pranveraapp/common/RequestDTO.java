package com.pranveraapp.common;

/**
 * Created by elion on 03/02/16.
 */
public interface RequestDTO {
    public String getRequestURI();

    public String getFullUrlQueryString();

    public Boolean isSecure();

    public void setRequestURI(String requestURI);

    public void setFullUrlQueryString(String fullUrlQueryString);

    public void setSecure(Boolean secure);
}
