package com.pranveraapp.core.web.controller.news;

import com.pranveraapp.common.web.controller.PranveraAppAbstractController;
import com.pranveraapp.core.author.domain.Author;
import com.pranveraapp.core.category.domain.Category;
import com.pranveraapp.core.search.domain.SearchCriteria;
import com.pranveraapp.core.search.domain.SearchResult;
import com.pranveraapp.core.search.service.SearchService;
import com.pranveraapp.core.tag.domain.Tag;
import com.pranveraapp.core.web.controller.news.support.PaginationInfo;
import com.pranveraapp.core.web.handler.AuthorHandlerMapping;
import com.pranveraapp.core.web.handler.NewsHandlerMapping;
import com.pranveraapp.core.web.handler.TagHandlerMapping;
import com.pranveraapp.core.web.service.SearchFacetDTOService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by elion on 03/02/16.
 */
public class PranveraAppNewsController extends PranveraAppAbstractController implements Controller {


    protected static String defaultCategoryView = "/news/index";
    protected static String NEWS_ATTRIBUTE_NAME = "newses";
    protected static String CATEGORY_ATTRIBUTE_NAME = "categories";
    protected static String TAG_ATTRIBUTE_NAME = "tags";
    protected static String AUTHOR_ATTRIBUTE_NAME = "authors";
    protected static String PAGINATIONINFO_ATTRIBUTE_NAME = "paginationInfo";

    protected static String CLICKONNEWSCATEGORY = "/news/category/";
    protected static String CLICKONTAG = "/news/tag/";
    protected static String CLICKONAUTHOR = "/news/author/";

    @Resource(name = "elSearchService")
    protected SearchService searchService;

    @Resource(name = "elSearchFacetDTOService")
    protected SearchFacetDTOService facetService;

    @Override
    @SuppressWarnings("unchecked")
    public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response)throws Exception{

        ModelAndView model = new ModelAndView();


        //List<SearchFacetDTO> availableFacets = getSearchService().getCategoryFacets(category);
        SearchCriteria searchCriteria = facetService.buildSearchCriteria(request);

        SearchResult result;
        SearchResult cResult = getAllCategories("/news/category/",searchCriteria);
        SearchResult tResult = getAllTags("/news/tag/");
        SearchResult aResult = getAllAuthors();



        String templatePath = getDefaultCategoryView();

        if(request.getServletPath().startsWith(CLICKONNEWSCATEGORY)){
            result = clickOnNews(request, searchCriteria);
        }
        else if(request.getServletPath().startsWith(CLICKONTAG)){
            result = clickOnTag(request, searchCriteria);
        }
        else if(request.getServletPath().startsWith(CLICKONAUTHOR)){
           result = clickOnAuthor(request,searchCriteria);
        }
        else {
            // this is the default link ,when we click on news button of our web app
            result = getSearchService().findSearchResultsByPostAndSearchCriteria(searchCriteria);
        }


        PaginationInfo paginationInfo = new PaginationInfo(result,searchCriteria);


        model.addObject(NEWS_ATTRIBUTE_NAME,result.getPosts());
        model.addObject(CATEGORY_ATTRIBUTE_NAME,cResult.getCategories());
        model.addObject(TAG_ATTRIBUTE_NAME,tResult.getTags());
        model.addObject(AUTHOR_ATTRIBUTE_NAME,aResult.getAuthors());
        model.addObject(PAGINATIONINFO_ATTRIBUTE_NAME,paginationInfo);
        model.setViewName(templatePath);

        return model;

    }

    /**
     * Get the necessary result attribute when the user clink on the news link of the website
     * @return
     */
    protected SearchResult clickOnNews(HttpServletRequest request,SearchCriteria searchCriteria){
        SearchResult result;
        if(request.getServletPath().equals("/news/")){
                result = getSearchService().findSearchResultsByPostAndSearchCriteria(searchCriteria);
            }else if(request.getServletPath().equals("/news/category/")){
                result = getSearchService().findSearchResultsByPostAndSearchCriteria(searchCriteria);
            }
            else{
                Category category = (Category)request.getAttribute(NewsHandlerMapping.CURRENT_NEWS_ATTRIBUTE_NAME);
                result = getSearchService().findSearchResultByCategory(category,searchCriteria);
            }
        return result;
    }

    /**
     * Get the necessary attribute when click on tab link
     * @param request
     * @param searchCriteria
     * @return
     */
    protected SearchResult clickOnTag(HttpServletRequest request, SearchCriteria searchCriteria){
        SearchResult result;
        if(request.getServletPath().equals("/news/tag/")){
                result = getSearchService().findSearchResultsByPostAndSearchCriteria(searchCriteria);
            }
            else{
                Tag tag = (Tag)request.getAttribute(TagHandlerMapping.CURRENT_NEWS_ATTRIBUTE_NAME);
                result = getSearchService().findSearchResultByTag(tag,searchCriteria);
            }
        return result;
    }

    /**
     * Get the necessary attribute when the user click on author's link
     * @param request
     * @param searchCriteria
     * @return
     */
    protected SearchResult clickOnAuthor(HttpServletRequest request, SearchCriteria searchCriteria){
        SearchResult result;
         if(request.getServletPath().equals("/news/author/")){
                result = getSearchService().findSearchResultsByPostAndSearchCriteria(searchCriteria);
            }
            else{
                Author author = (Author)request.getAttribute(AuthorHandlerMapping.CURRENT_NEWS_ATTRIBUTE_NAME);
                result = getSearchService().findSearchResultByAuthor(author,searchCriteria);
            }
        return result;
    }

    protected SearchResult getAllCategories(String post_category, SearchCriteria searchCriteria){

        return getSearchService().findSearchResultCategories(post_category,searchCriteria);
    }

    protected SearchResult getAllTags(String all){
        return getSearchService().findSearchResultTags(all);
    }

    protected SearchResult getAllAuthors(){
        return getSearchService().findSearchResultAuthors();
    }

    protected SearchService getSearchService(){
        return searchService;
    }

    public String getDefaultCategoryView(){
        return defaultCategoryView;
    }
}
