package com.pranveraapp.core.search.service;

import com.pranveraapp.core.author.domain.Author;
import com.pranveraapp.core.author.service.AuthorService;
import com.pranveraapp.core.category.domain.Category;
import com.pranveraapp.core.category.service.CategoryService;
import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.post.dto.PostDTO;
import com.pranveraapp.core.post.service.PostService;
import com.pranveraapp.core.search.domain.SearchCriteria;
import com.pranveraapp.core.search.domain.SearchResult;
import com.pranveraapp.core.tag.domain.Tag;
import com.pranveraapp.core.tag.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by elion on 20/01/16.
 */
@Service("elSearchService")
public class SearchServiceImpl implements SearchService {

    @Resource(name = "elPostService")
    protected PostService postService;

    @Resource(name = "elCategoryService")
    protected CategoryService categoryService;

    @Resource(name = "elTagService")
    protected TagService tagService;

    @Resource(name = "elAuthorService")
    protected AuthorService authorService;

    @Override
    public SearchResult findSearchResultCategories(String post_category, SearchCriteria searchCriteria){

        Map<Long,Integer> itemOfEachCategory = new HashMap<Long,Integer>();

        SearchResult categoriesResult = new SearchResult();
        List<Category> categories = categoryService.findAllCategories(post_category);

        for (Category category: categories){
            if(category.getId()!=4)
            {
                itemOfEachCategory.put(category.getId(),findItemsByCategory(category));
            }
            // for case of see all features i query the findAllPosts method
            else {
                itemOfEachCategory.put(category.getId(),postService.findAllPosts().size());
            }
        }
        categoriesResult.setCategories(categories,itemOfEachCategory);
        return categoriesResult;

    }


    @Override
    public SearchResult findSearchResultsByPostAndSearchCriteria(SearchCriteria searchCriteria){

        SearchResult result = new SearchResult();
        Integer totalResults = postService.findAllPosts().size();
        List<Post> posts = postService.findAllPostsBySearchCriteria(searchCriteria);
        result.setPosts(posts);
        result.setTotalResults(totalResults);
        result.setPageSize(searchCriteria.getPageSize());
        result.setPage(1);
        return result;
    }

    @Override
    public SearchResult findSearchResultTags(String all){

        SearchResult tagsResult = new SearchResult();
                tagsResult.setTags(tagService.findAllTags(all));
        return  tagsResult;
    }

    @Override
    public SearchResult findSearchResultAuthors(){

        SearchResult authorsResult = new SearchResult();
                authorsResult.setAuthors(authorService.findAllAuthor());
        return  authorsResult;
    }

    @Override
    public SearchResult findSearchResultByCategory(Category category,SearchCriteria searchCriteria) {
        SearchResult result= new SearchResult();
        List<Post> posts = postService.findFilteredActivePostsByCategory(category,searchCriteria);
        result.setPosts(posts);
        result.setTotalResults(posts.size());
        result.setPage(1);
        result.setPageSize(posts.size());
        return result;
    }

    @Override
    public Integer findItemsByCategory(Category category){
        return  postService.findFilteredItemsByCategory(category);
    }

    @Override
    public SearchResult findSearchResultByTag(Tag tag,SearchCriteria searchCriteria) {
        SearchResult result= new SearchResult();
        List<Post> posts = tagService.findFilteredActivePostsByTag(tag.getId(),searchCriteria);
        Integer totalResults = tagService.findTotalResultsByTag(tag.getId());
        result.setPosts(posts);
        result.setTotalResults(totalResults);
        result.setPage(1);
        result.setPageSize(searchCriteria.getPageSize());
        return result;
    }

    @Override
    public SearchResult findSearchResultTagsByPost(Long postId){
        SearchResult result = new SearchResult();
        List<Tag> tags = tagService.findFilteredActiveTagsByPost(postId);
        result.setTags(tags);
        return result;
    }

    @Override
    public SearchResult findSearchResultByAuthor(Author author,SearchCriteria searchCriteria) {
        SearchResult result= new SearchResult();
        List<Post> posts = authorService.findFilteredActivePostsByAuthor(author.getId(),searchCriteria);
        Integer totalResults =  authorService.findTotalResultsPostsByAuthor(author.getId());
        result.setPosts(posts);
        result.setTotalResults(totalResults);
        result.setPage(1);
        result.setPageSize(searchCriteria.getPageSize());
        return result;
    }

   /* *//**
     * Perform any necessary conversion of the key to be used by the search service
     * @param criteria
     *//*
    protected  void setQualifiedKeys(SearchCriteria criteria){
        //Convert the filter criteria url keys
        Map<String, String[]> convertedFilterCriteria =new HashMap<String, String []>();
        for(Map.Entry<String, String []> entry: criteria.getFilterCriteria().entrySet()){
            Filed field = fieldDao.readFieldByAbbreviation(entry.getKey());
            if(field !=null)
            {
                String qualifiedFieldName = getDatabaseQualifiedFieldName(field.getQualifiedFieldName());
                convertedFilterCriteria.put(qualifiedFieldName, entry.getValue());
            }
        }

        //Convert the sort criteria url key
        if(StringUtils.isNotBlank(criteria.getSortQuery())){
            StringBuilder convertedSortQuery = new StringBuilder();
            for(String sortQuery: criteria.getSortQuery().split(",")){
                String[] sort = sortQuery.split(" ");
                if(sort.length == 2){
                    String key = sort[0];
                    Field field = fieldDao.readFieldByAbbreviation(key);
                    String qualifiedFieldName = getDatabaseQuelifiedFieldName(field.getQuelifiedFieldName());

                    if(convertedSortQuery.length() > 0){
                        convertedSortQuery.append(",");
                    }

                    convertedSortQuery.append(qualifiedFieldName).append(" ").append(sort[1]);
                }

            }
            criteria.setSortQuery(convertedSortQuery.toString());
        }
    }

    protected String getDatabaseQualifiedFieldName(String qualifiedFieldName){
        return qualifiedFieldName;
    }
*/

























}
