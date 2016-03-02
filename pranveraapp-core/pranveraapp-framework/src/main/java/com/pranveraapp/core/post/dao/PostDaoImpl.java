package com.pranveraapp.core.post.dao;

import com.pranveraapp.core.lokale.domain.Lokale;
import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.post.dto.PostDTO;
import com.pranveraapp.core.search.domain.SearchCriteria;
import com.pranveraapp.core.util.FunctionUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by elion on 20/01/16.
 */
@Repository("elPostDao")
public class PostDaoImpl implements PostDao {

    @PersistenceContext(unitName = "elPU")
    protected EntityManager em;


    @Override
    public List<Post> readAllPosts() {
        return readAllPostsInternal();
    }

    /**
     * Here we retrieve all the posts items
     *
     * @param searchCriteria
     * @return
     */
    @Override
    public List<Post> readAllPostsBySearchCriteria(SearchCriteria searchCriteria) {
        List<Post> retval = new ArrayList<Post>();

        TypedQuery<Post> typedQuery = em.createNamedQuery("EL_READ_ALL_POSTS", Post.class);
        typedQuery.setFirstResult(FunctionUtils.prepareFirstResultCriteria(searchCriteria));
        typedQuery.setMaxResults(searchCriteria.getPageSize());
        return typedQuery.getResultList();


   }


    /*Questo metodo legge dal file orm una query*/
    protected List<Post> readAllPostsInternal() {

        TypedQuery<Post> typedQuery = em.createNamedQuery("EL_READ_ALL_POSTS", Post.class);
        return typedQuery.getResultList();
    }

    @Override
    public List<Post> readFilteredActivePostsByCategory(Long categoryId, SearchCriteria searchCriteria) {

        return readFilteredActivePostsByCategoryInternal(categoryId, searchCriteria);
    }

    @Override
    public Integer readFilteredItemsByCategory(Long categoryId) {
        TypedQuery<Post> query = em.createNamedQuery("EL_READ_ACTIVE_POSTS_BY_CATEGORY", Post.class);
        query.setParameter("categoryId", Arrays.asList(categoryId));

        return query.getResultList().size();
    }

    protected List<Post> readFilteredActivePostsByCategoryInternal(Long categoryId, SearchCriteria searchCriteria) {

        TypedQuery<Post> query = em.createNamedQuery("EL_READ_ACTIVE_POSTS_BY_CATEGORY", Post.class);
        query.setParameter("categoryId", Arrays.asList(categoryId));
        query.setFirstResult(FunctionUtils.prepareFirstResultCriteria(searchCriteria));
        query.setMaxResults(searchCriteria.getPageSize());

        return query.getResultList();
    }

    @Override
    public List<Post> findPostByURI(String uri){

        Query query;
        query = em.createNamedQuery("EL_READ_POSTS_BY_OUTGOING_URL");
        query.setParameter("url",uri);

        List<Post> results = query.getResultList();
        return  results;
    }

}





























