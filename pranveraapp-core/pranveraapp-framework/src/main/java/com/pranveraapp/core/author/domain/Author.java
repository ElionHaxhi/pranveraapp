package com.pranveraapp.core.author.domain;

import java.io.Serializable;

/**
 * Created by elion on 31/01/16.
 */
public interface Author extends Serializable{

    public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

    public String getImgUrl();

    public void setImgUrl(String imgUrl);

    public String getTwitterUrl();

    public void setTwitterUrl(String twitterUrl);

    public String getAuthorUrl();

    public void setAuthorUrl(String authorUrl);
}
