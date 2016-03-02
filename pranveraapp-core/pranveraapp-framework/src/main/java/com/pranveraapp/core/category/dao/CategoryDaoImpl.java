package com.pranveraapp.core.category.dao;

import com.pranveraapp.core.category.domain.Category;
import org.hibernate.ejb.QueryHints;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by elion on 03/02/16.
 */
@Repository("elCategoryDao")
public class CategoryDaoImpl implements  CategoryDao {


    @PersistenceContext(unitName="elPU")
    protected EntityManager em;


    @Override
    public Category findCategoryByURI(String uri){
        Query query;
        query = em.createNamedQuery("EL_READ_CATEGORY_OUTGOING_URL");
        query.setParameter("url",uri);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setHint(QueryHints.HINT_CACHE_REGION,"query.Category");

        @SuppressWarnings("unchecked")
        List<Category> retval = query.getResultList();
        if(retval !=null && !retval.isEmpty()){
            return  retval.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public List<Category> readAllCategories(String post_category){
        Query query;
        query = em.createNamedQuery("EL_READ_ALL_CATEGORY");
        query.setParameter("post_category",post_category);
        return query.getResultList();
    }
}
