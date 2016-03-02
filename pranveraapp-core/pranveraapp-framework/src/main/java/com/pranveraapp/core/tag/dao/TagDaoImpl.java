package com.pranveraapp.core.tag.dao;

import com.pranveraapp.core.category.domain.CategoryImpl;
import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.post.domain.PostImpl;
import com.pranveraapp.core.posttagxref.domain.PostTagXref;
import com.pranveraapp.core.posttagxref.domain.PostTagXrefImpl;
import com.pranveraapp.core.search.domain.SearchCriteria;
import com.pranveraapp.core.tag.domain.Tag;
import com.pranveraapp.core.util.FunctionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by elion on 10/02/16.
 */
@Repository("elTagDao")
public class TagDaoImpl implements TagDao {

    @PersistenceContext(name = "elPU")
    protected EntityManager em;

    @Override
    public List<Tag> readAllTags(String all){
        Query query;
        query = em.createNamedQuery("EL_READ_ALL_TAG");
        query.setParameter("all",all);
        return query.getResultList();
    }

    @Override
    public Tag readTagByURI(String uri){
        Query query;
        query = em.createNamedQuery("EL_READ_TAG_OUTGOING_URL");
        query.setParameter("url",uri);

        List<Tag> tags = query.getResultList();
        if(tags !=null && !tags.isEmpty()){
            return tags.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public List<Tag> readFilteredActiveTagsByPost(Long postId){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
        Root<PostTagXrefImpl> tagXrefRoot = criteria.from(PostTagXrefImpl.class);

        Join<PostTagXrefImpl,Post> post =tagXrefRoot.join("post");
        Join<PostTagXrefImpl,Tag> tag= tagXrefRoot.join("tag");

        criteria.select(tag);
        List<Predicate> restrictions = new ArrayList<Predicate>();
        restrictions.add(post.get("id").in(Arrays.asList(postId)));

        criteria.where(restrictions.toArray(new Predicate[restrictions.size()]));

        TypedQuery<Tag> typedQuery = em.createQuery(criteria);
        return typedQuery.getResultList();

    }

    @Override
    public List<Post> readFilteredActivePostsByTag(Long tagId,SearchCriteria searchCriteria){
        return readFilteredActivePostByTagInternal(tagId,searchCriteria);
    }

    public List<Post> readFilteredActivePostByTagInternal(Long tagId,SearchCriteria searchCriteria){

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Post> criteria = builder.createQuery(Post.class);

        Root<PostTagXrefImpl> tagXrefRoot = criteria.from(PostTagXrefImpl.class);

        Join<PostTagXrefImpl,Post> post =tagXrefRoot.join("post");
        Join<PostTagXrefImpl,Tag> tag= tagXrefRoot.join("tag");

        criteria.select(post);

        // We only want results from the determine category
        List<Predicate> restrictions = new ArrayList<Predicate>();
        restrictions.add(tag.get("id").in(Arrays.asList(tagId)));


        criteria.where(restrictions.toArray(new Predicate[restrictions.size()]));

        TypedQuery<Post> typedQuery = em.createQuery(criteria);
        typedQuery.setFirstResult(FunctionUtils.prepareFirstResultCriteria(searchCriteria));
        typedQuery.setMaxResults(searchCriteria.getPageSize());


        return typedQuery.getResultList();
    }

    public Integer readTotalResultsByTag(Long tagId){

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Post> criteria = builder.createQuery(Post.class);

        Root<PostTagXrefImpl> tagXrefRoot = criteria.from(PostTagXrefImpl.class);

        Join<PostTagXrefImpl,Post> post =tagXrefRoot.join("post");
        Join<PostTagXrefImpl,Tag> tag= tagXrefRoot.join("tag");

        criteria.select(post);

        // We only want results from the determine category
        List<Predicate> restrictions = new ArrayList<Predicate>();
        restrictions.add(tag.get("id").in(Arrays.asList(tagId)));


        criteria.where(restrictions.toArray(new Predicate[restrictions.size()]));

        TypedQuery<Post> typedQuery = em.createQuery(criteria);


        return typedQuery.getResultList().size();
    }
}


















