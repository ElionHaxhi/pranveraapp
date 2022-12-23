package com.pranveraapp.core.author.service;

import com.pranveraapp.core.author.dao.AuthorDao;
import com.pranveraapp.core.author.domain.Author;
import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.search.domain.SearchCriteria;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by elion on 01/02/16.
 */
@Service("elAuthorService")
public class AuthorServiceImpl implements AuthorService {

    @Resource(name ="elAuthorDao")
    protected AuthorDao authorDao;

    public List<Author> findAllAuthor(){
        return authorDao.readAllAuthor();
    }

    public Author findAuthorByURI(String uri){
        return authorDao.readAuthorByURI(uri);
    }

    public Author findAuthorById(Long id){
        return authorDao.readAuthorById(id);
    }

    public List<Post> findFilteredActivePostsByAuthor(Long authorId,SearchCriteria searchCriteria){
        return authorDao.readFilteredActivePostsByAuthor(authorId,searchCriteria);
    }

    public Integer findTotalResultsPostsByAuthor(Long authorId){
        return authorDao.readTotalResultsPostsByAuthor(authorId);
    }
}
