package com.pranveraapp.core.author.service;

import com.pranveraapp.core.author.domain.Author;
import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.search.domain.SearchCriteria;

import java.util.List;

/**
 * Created by elion on 01/02/16.
 */
public interface AuthorService {

    public List<Author> findAllAuthor();

    public Author findAuthorByURI(String uri);

    public List<Post> findFilteredActivePostsByAuthor(Long authorId,SearchCriteria searchCriteria);

    public Integer findTotalResultsPostsByAuthor(Long authorId);
}
