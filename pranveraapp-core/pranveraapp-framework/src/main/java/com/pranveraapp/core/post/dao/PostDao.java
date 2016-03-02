package com.pranveraapp.core.post.dao;

import java.util.Date;
import java.util.List;

import com.pranveraapp.core.post.dto.PostDTO;
import com.pranveraapp.core.search.domain.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pranveraapp.core.post.Lang;
import com.pranveraapp.core.post.domain.Post;



public interface PostDao {

    public List<Post> readAllPosts();

    public List<Post> readAllPostsBySearchCriteria(SearchCriteria searchCriteria);

    public List<Post> readFilteredActivePostsByCategory(Long categoryId,SearchCriteria searchCriteria);

    public Integer readFilteredItemsByCategory(Long categoryId);

    public List<Post> findPostByURI(String uri);

}

