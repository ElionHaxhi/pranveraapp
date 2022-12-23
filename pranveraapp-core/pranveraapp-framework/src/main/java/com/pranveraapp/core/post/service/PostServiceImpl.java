package com.pranveraapp.core.post.service;


import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.pranveraapp.core.category.domain.Category;
import com.pranveraapp.core.post.dto.PostDTO;
import com.pranveraapp.core.search.domain.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.pranveraapp.common.support.DateFactory;
import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.post.Lang;
import com.pranveraapp.core.post.dao.PostDao;

import javax.annotation.Resource;

@Service("elPostService")
public class PostServiceImpl implements PostService{

    @Resource(name = "elPostDao")
    protected PostDao postDao;

    @Override
    public List<Post> findAllPosts(){
       // System.out.println(postDao.readAllPosts());
        return postDao.readAllPosts();
    }

    @Override
    public  List<Post> findAllPostsBySearchCriteria(SearchCriteria searchCriteria){

        return postDao.readAllPostsBySearchCriteria(searchCriteria);

    }

    @Override
    public  List<Post> findFilteredActivePostsByCategory(Category category, SearchCriteria searchCriteria){
        return postDao.readFilteredActivePostsByCategory(category.getId(), searchCriteria);
    }

    @Override
    public Integer findFilteredItemsByCategory(Category category){
        return postDao.readFilteredItemsByCategory(category.getId());
    }

    @Override
    public Post findPostByURI(String uri){

        List<Post> posts = postDao.findPostByURI(uri);
        if(posts == null || posts.size() == 0){
            return null;
        } else {
            return posts.get(0);
        }
    }

}

