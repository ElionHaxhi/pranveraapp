package com.pranveraapp.core.web.handler;

import com.pranveraapp.common.web.PranveraAppRequestContext;
import com.pranveraapp.core.author.domain.Author;
import com.pranveraapp.core.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * Created by elion on 11/02/16.
 */
public class AuthorHandlerMapping extends ELAbstractHandlerMapping {

    private String controllerName = "elNewsController";

    protected String defaultTemplateName = "/layout/index/";

    @Resource(name = "elAuthorService")
    protected AuthorService authorService;

    @Value("${request.uri.encoding}")
    public String charEncoding;

    public static final String CURRENT_NEWS_ATTRIBUTE_NAME = "news";

    @Override
    protected Object getHandlerInternal(HttpServletRequest request) throws Exception{

        PranveraAppRequestContext context = PranveraAppRequestContext.getPranveraAppRequestContext();

        if(context !=null && context.getRequestURIWithoutContext() !=null)
        {
            String requestUri = URLDecoder.decode(context.getRequestURIWithoutContext(),charEncoding);

            Author author = authorService.findAuthorByURI(requestUri);

            if(author!=null){
                context.getRequest().setAttribute(CURRENT_NEWS_ATTRIBUTE_NAME,author);
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
