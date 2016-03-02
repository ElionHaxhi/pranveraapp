package com.pranveraapp.core.category.service;

import com.pranveraapp.core.category.dao.CategoryDao;
import com.pranveraapp.core.category.domain.Category;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by elion on 03/02/16.
 */
@Service("elCategoryService")
public class CategoryServiceImpl implements CategoryService {

    @Resource(name = "elCategoryDao")
    protected CategoryDao categoryDao;

    @Override
    public Category findCategoryByURI(String uri){
        return categoryDao.findCategoryByURI(uri);
    }

    @Override
    public List<Category> findAllCategories(String post_category){
        return categoryDao.readAllCategories(post_category);
    }

}
