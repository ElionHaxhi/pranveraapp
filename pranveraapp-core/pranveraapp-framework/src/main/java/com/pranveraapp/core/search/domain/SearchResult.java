package com.pranveraapp.core.search.domain;

import com.pranveraapp.core.author.domain.Author;
import com.pranveraapp.core.category.domain.Category;
import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.post.dto.PostDTO;
import com.pranveraapp.core.tag.domain.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by elion on 20/01/16.
 */
public class SearchResult {

    protected List<Post> posts;
    protected List<CategoryResult> categoriesResult;
    protected List<Tag> tags;
    protected List<Author> authors;

    protected Integer totalResults;
    protected Integer page;
    protected Integer pageSize;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

      public Integer getStartResult() {
        return ((posts == null || posts.size() == 0)) ? 0 : ((page - 1) * pageSize) + 1;
    }

    public Integer getEndResult() {
        return Math.min(page * pageSize, totalResults);
    }

    public Integer getTotalPages() {
        return ((posts == null || posts.size() == 0)) ? 1 : (int) Math.ceil(totalResults * 1.0 / pageSize);
    }

    public List<CategoryResult> getCategories() {
        return categoriesResult;
    }

    public void setCategories(List<Category> categories,Map<Long,Integer> itemOfEachCategory) {
        categoriesResult = new ArrayList<CategoryResult>();
        for(Category category : categories){
            categoriesResult.add(new CategoryResult(category.getName(),category.getUrl(),itemOfEachCategory.get(category.getId()).toString()));
        }
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags)
    {
        this.tags = tags;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
