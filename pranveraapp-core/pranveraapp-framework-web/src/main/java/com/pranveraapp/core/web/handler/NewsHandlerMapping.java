package com.pranveraapp.core.web.handler;

import com.pranveraapp.common.web.PranveraAppRequestContext;
import com.pranveraapp.core.category.domain.Category;
import com.pranveraapp.core.category.service.CategoryService;
import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.post.service.PostService;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * Created by elion on 02/02/16.
 */
public class NewsHandlerMapping extends ELAbstractHandlerMapping{

    private String controllerName = "elNewsController";

    protected String defaultTemplateName = "/layout/index";

    @Resource(name = "elCategoryService")
    private CategoryService categoryService;


    @Value("${request.uri.encoding}")
    public String charEncoding;

    public static final  String CURRENT_NEWS_ATTRIBUTE_NAME = "news";

    @Override
    protected Object getHandlerInternal(HttpServletRequest request)throws
    Exception{
        PranveraAppRequestContext context = PranveraAppRequestContext.getPranveraAppRequestContext();

        if(context !=null && context.getRequestURIWithoutContext() != null){
            String requestUri = URLDecoder.decode(context.getRequestURIWithoutContext(),charEncoding);

            Category category = categoryService.findCategoryByURI(requestUri);

            if(category !=null){
                context.getRequest().setAttribute(CURRENT_NEWS_ATTRIBUTE_NAME,category);
                return controllerName;
            }
        }
        return null;
    }

    public String getDefaultTemplateName() {
        return defaultTemplateName;
    }

    public void setDefaultTemplateName(String defaultTemplateName) {
        this.defaultTemplateName = defaultTemplateName;
    }


}
