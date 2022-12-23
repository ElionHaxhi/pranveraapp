package com.pranveraapp.core.web.handler;

import com.pranveraapp.common.web.PranveraAppRequestContext;
import com.pranveraapp.core.tag.domain.Tag;
import com.pranveraapp.core.tag.service.TagService;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * Created by elion on 10/02/16.
 */
public class TagHandlerMapping extends ELAbstractHandlerMapping{

    private String controllerName="elNewsController";

    protected String defaultTemplateName = "/layout/index";

    @Resource(name = "elTagService")
    protected TagService tagService;


    @Value("${request.uri.encoding}")
    public String charEncoding;


    public static final  String CURRENT_NEWS_ATTRIBUTE_NAME = "post";

        @Override
    protected Object getHandlerInternal(HttpServletRequest request)throws
    Exception{
        PranveraAppRequestContext context = PranveraAppRequestContext.getPranveraAppRequestContext();

        if(context !=null && context.getRequestURIWithoutContext() != null){
            String requestUri = URLDecoder.decode(context.getRequestURIWithoutContext(), charEncoding);

            Tag tag = tagService.findTagByURI(requestUri);

            if(tag !=null){
                context.getRequest().setAttribute(CURRENT_NEWS_ATTRIBUTE_NAME,tag);
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
