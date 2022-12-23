package com.pranveraapp.core.web.handler;

import com.pranveraapp.common.web.PranveraAppRequestContext;
import com.pranveraapp.core.post.domain.Post;
import com.pranveraapp.core.post.service.PostService;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * This handler mapping works with the Post entity to determine if a post has been configured for the passed in URL
 * If the URL matches a valid Post then this mapping returns the handler configured via the controllerName
 * property or elPostController by default.
 *
 * Created by elion on 28/02/16.
 */
public class PostHandlerMapping extends ELAbstractHandlerMapping{

    private final String controllerName = "elPostController";

    @Resource(name = "elPostService")
    private PostService postService;

    @Value("${request.uri.encoding}")
    public String charEncoding;

    public static final String CURRENT_POST_ATTRIBUTE_NAME = "currentPost";

    @Override
    protected Object getHandlerInternal(HttpServletRequest request) throws Exception{

        PranveraAppRequestContext context = PranveraAppRequestContext.getPranveraAppRequestContext();
        if(context !=null && context.getRequestURIWithoutContext() !=null){
            String requestUri = URLDecoder.decode(context.getRequestURIWithoutContext(),charEncoding);
            Post post = postService.findPostByURI(requestUri);
            if(post != null){
                context.getRequest().setAttribute(CURRENT_POST_ATTRIBUTE_NAME, post);
                return controllerName;
            }
        }
        return null;
    }
}
