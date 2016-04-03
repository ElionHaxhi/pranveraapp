/**
 * 
 *	here we can implements some service wich can do some logic retriving infomation from 
 *	rdbms throw the jpa and hibernate.
 *  Also this class is the parent one which is called from the child pattern class. 
 */
package com.pranveraapp.core.web.api.endpoint;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.pranveraapp.common.web.PranveraAppWebRequestProcessor;
import com.pranveraapp.core.author.domain.Author;
import com.pranveraapp.core.author.service.AuthorService;
import com.pranveraapp.core.web.api.PranveraAppWebServicesException;
import com.pranveraapp.core.web.api.wrapper.AuthorWrapper;

/**
 * @author elion
 *
 */
public abstract class TestEndpoint extends BaseEndPoint {
	
	@Resource(name = "elAuthorService")
	protected AuthorService authorService;
	
	
	public AuthorWrapper findAuthorByUri(HttpServletRequest request, String uri){
		Author author = authorService.findAuthorByURI(uri);
		if(author != null){
			AuthorWrapper wrapper;
			wrapper = (AuthorWrapper) context.getBean(AuthorWrapper.class.getName());
			wrapper.wrapDetails(author, request);
			return wrapper;
		}
		throw PranveraAppWebServicesException.build(HttpStatus.NOT_FOUND.value())
			.addMessage(PranveraAppWebServicesException.AUTHOR_NOT_FOUND, uri);
	}
}
