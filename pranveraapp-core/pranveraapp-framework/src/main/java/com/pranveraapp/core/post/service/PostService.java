package com.pranveraapp.core.post.service;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.pranveraapp.core.category.domain.Category;
import com.pranveraapp.core.post.dto.PostDTO;
import com.pranveraapp.core.search.domain.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pranveraapp.core.post.Lang;
import com.pranveraapp.core.post.domain.Post;

public interface PostService {
	

    public List<Post> findAllPosts();

    public List<Post> findAllPostsBySearchCriteria(SearchCriteria searchCriteria);

    public List<Post> findFilteredActivePostsByCategory(Category category, SearchCriteria searchCriteria);

    public Integer findFilteredItemsByCategory(Category category);

    public Post findPostByURI(String uri);
}
