/**
 */
package com.pranveraapp.site.api.endpoint;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pranveraapp.core.web.api.wrapper.AuthorWrapper;

/**
 * @author elion
 *
 */
@RestController
@RequestMapping(value = "/test/",
                produces = {MediaType.APPLICATION_JSON_VALUE},
                consumes = {MediaType.APPLICATION_JSON_VALUE})
public class TestEndpoint extends com.pranveraapp.core.web.api.endpoint.TestEndpoint {

	@Override
	@RequestMapping(value = "author/{id}", method = RequestMethod.GET)
	public AuthorWrapper findAuthorById(HttpServletRequest request, @PathVariable("id") Long id){

		return super.findAuthorById(request, id);
		
	}
	
}
