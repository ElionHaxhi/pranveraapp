package com.pranveraapp.core.search.domain;

/**
 * Created by elion on 10/02/16.
 */
public class CategoryResult {
    protected String name;
    protected String url;
    protected String itemOfCategory;

    public CategoryResult(String name,String url,String itemOfCategory){
        this.name = name;
        this.url = url;
        this.itemOfCategory = itemOfCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getItemOfCategory() {
        return " ("+itemOfCategory+")";
    }

    public void setItemOfCategory(String itemOfCategory) {
        this.itemOfCategory = itemOfCategory;
    }
}
