package com.pranveraapp.core.category.dao;

import com.pranveraapp.core.category.domain.Category;

import java.io.Serializable;
import java.util.List;

/**
 * Created by elion on 03/02/16.
 */
public interface CategoryDao extends Serializable {
    public Category findCategoryByURI(String uri);

    public List<Category> readAllCategories(String post_category);
}
