package com.pranveraapp.core.author.dao;

import com.pranveraapp.core.author.domain.Author;
import com.pranveraapp.core.author.domain.AuthorImpl;
import com.pranveraapp.core.post.dao.PostDao;
import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.search.domain.SearchCriteria;
import com.pranveraapp.core.util.FunctionUtils;
import org.hibernate.ejb.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by elion on 01/02/16.
 */
@Repository("elAuthorDao")
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext(unitName = "elPU")
    protected EntityManager em;

    @Override
    public List<Author> readAllAuthor(){

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Author> criteria = builder.createQuery(Author.class);
        Root<AuthorImpl> authors = criteria.from(AuthorImpl.class);

        criteria.select(authors);

        TypedQuery<Author> query = em.createQuery(criteria);
        query.setHint(QueryHints.HINT_CACHEABLE, true);

        return query.getResultList();
    }

    @Override
    public Author readAuthorByURI(String uri){

        Query query;
        query = em.createNamedQuery("EL_READ_AUTHOR_BY_URI");
        query.setParameter("authorUrl", uri);


        List<Author> authors=query.getResultList();
        if(authors !=null && !authors.isEmpty()){
         return authors.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public List<Post> readFilteredActivePostsByAuthor(Long authorId,SearchCriteria searchCriteria){
        Query query;
        query = em.createNamedQuery("EL_READ_POSTS_BY_AUTHORID");
        query.setParameter("authorId", authorId);
        query.setFirstResult(FunctionUtils.prepareFirstResultCriteria(searchCriteria));
        query.setMaxResults(searchCriteria.getPageSize());
        return query.getResultList();
    }

    @Override
    public Integer readTotalResultsPostsByAuthor(Long authorId){
         Query query;
        query = em.createNamedQuery("EL_READ_POSTS_BY_AUTHORID");
        query.setParameter("authorId", authorId);
        return (Integer)query.getResultList().size();
    }
}

