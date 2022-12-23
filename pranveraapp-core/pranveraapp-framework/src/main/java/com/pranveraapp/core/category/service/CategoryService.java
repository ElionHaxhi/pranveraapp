package com.pranveraapp.core.category.service;

import com.pranveraapp.core.category.domain.Category;

import java.io.Serializable;
import java.util.List;

/**
 * Created by elion on 03/02/16.
 */
public interface CategoryService extends Serializable {
    public Category findCategoryByURI(String uri);

    public List<Category> findAllCategories(String post_category);
}
