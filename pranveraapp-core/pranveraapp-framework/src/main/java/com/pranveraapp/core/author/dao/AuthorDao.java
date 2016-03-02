package com.pranveraapp.core.author.dao;

import com.pranveraapp.core.author.domain.Author;
import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.search.domain.SearchCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by elion on 01/02/16.
 */
public interface AuthorDao {

    public List<Author> readAllAuthor();

    public Author readAuthorByURI(String uri);

    public List<Post> readFilteredActivePostsByAuthor(Long authorId, SearchCriteria searchCriteria);

    public Integer readTotalResultsPostsByAuthor(Long authorId);
}


