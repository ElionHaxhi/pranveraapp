package com.pranveraapp.core.search.service;

import com.pranveraapp.core.author.domain.Author;
import com.pranveraapp.core.category.domain.Category;
import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.search.domain.SearchCriteria;
import com.pranveraapp.core.search.domain.SearchResult;
import com.pranveraapp.core.tag.domain.Tag;

import java.util.List;

/**
 * Created by elion on 20/01/16.
 */
public interface SearchService {

    public Integer findItemsByCategory(Category category);


    public SearchResult findSearchResultsByPostAndSearchCriteria(SearchCriteria searchCriteria);

    public SearchResult findSearchResultByCategory(Category category,SearchCriteria searchCriteria);

    public SearchResult findSearchResultCategories(String post_category,SearchCriteria searchCriteria);

    public SearchResult findSearchResultTags(String all);

    public SearchResult findSearchResultTagsByPost(Long postId);

    public SearchResult findSearchResultAuthors();

    public SearchResult findSearchResultByTag(Tag tag,SearchCriteria searchCriteria);

    public SearchResult findSearchResultByAuthor(Author author,SearchCriteria searchCriteria);
}
