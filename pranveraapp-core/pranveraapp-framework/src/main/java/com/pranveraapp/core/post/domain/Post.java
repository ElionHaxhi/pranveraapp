package com.pranveraapp.core.post.domain;

import com.pranveraapp.core.author.domain.Author;
import com.pranveraapp.core.category.domain.Category;
import com.pranveraapp.core.posttagxref.domain.PostTagXref;
import com.pranveraapp.core.timetoread.domain.TimeToRead;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by elion on 31/01/16.
 */
public interface Post extends Serializable{
    public Long getId();

    public void setId(Long id);

    public String getTitle();

    public void setTitle(String title);

    public String getRenderedContent();

    public void setRenderedContent(String renderedContent);

    public String getRenderedSummary();

    public void setRenderedSummary(String renderedSummary);

    public Date getPublishAt();

    public void setPublishAt(Date publishAt);

    public String getImgUrl();

    public void setImgUrl(String imgUrl);

    public String getUrl();

    public void setUrl(String url);

    public Category getCategory();

    public void setCategory(Category category);

    public Author getAuthor();

    public void setAuthor(Author author);

    public List<PostTagXref> getPostTagXrefs();

    public void setPostTagXrefs(List<PostTagXref> postTagXrefs);

    public TimeToRead getTimeToRead();

    public void setTimeToRead(TimeToRead timeToRead);
}
