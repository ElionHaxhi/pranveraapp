package com.pranveraapp.core.web.controller.post;

import com.pranveraapp.common.web.controller.PranveraAppAbstractController;
import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.search.domain.SearchResult;
import com.pranveraapp.core.search.service.SearchService;
import com.pranveraapp.core.web.handler.PostHandlerMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by elion on 28/02/16.
 */
public class PranveraAppPostController extends PranveraAppAbstractController implements Controller {

    protected  static String MODEL_ATTRIBUTE_NAME = "post";
    protected  static String TAGS_BY_POST = "tags";
    protected  static String defaultPostView = "news/show";

    @Resource(name = "elSearchService")
    SearchService searchService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{

        SearchResult tResult;


        ModelAndView model = new ModelAndView();
        Post post = (Post) request.getAttribute(PostHandlerMapping.CURRENT_POST_ATTRIBUTE_NAME);


        tResult = getAllTagsByPost(post.getId());


        model.addObject(MODEL_ATTRIBUTE_NAME,post);
        model.addObject(TAGS_BY_POST,tResult.getTags());
        model.setViewName(getDefaultPostView());

        return model;
    }

    public SearchResult getAllTagsByPost(Long postId){
        return searchService.findSearchResultTagsByPost(postId);
    }



    public String getDefaultPostView(){
        return defaultPostView;
    }
}
