package com.pranveraapp.core.web.api.wrapper;
/**
 * 
 */

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.pranveraapp.core.author.domain.Author;

/**
 * @author elion
 *
 */
@XmlRootElement(name = "author")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AuthorWrapper extends BaseWrapper implements APIWrapper<Author> {

	@XmlElement
    protected String name;

	@XmlElement
    protected String imgUrl;

	@XmlElement
    protected String twitterUrl;

	@XmlElement
    protected String authorUrl;
	
	@Override
	public void wrapDetails(Author model, HttpServletRequest request){
		this.name = model.getName();
		this.imgUrl = model.getImgUrl();
		this.twitterUrl = model.getTwitterUrl();
		this.authorUrl = model.getAuthorUrl();
	}
	
	@Override
	public void wrapSummary(Author model, HttpServletRequest request){
		wrapDetails(model, request);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTwitterUrl() {
		return twitterUrl;
	}

	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}

	public String getAuthorUrl() {
		return authorUrl;
	}

	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
	}
	
	
}
