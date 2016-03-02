package com.pranveraapp.core.post.dto;

import com.pranveraapp.core.author.domain.Author;
import com.pranveraapp.core.category.domain.Category;
import com.pranveraapp.core.timetoread.domain.TimeToRead;

import java.util.Date;

/**
 * Created by elion on 22/02/16.
 */
public class PostDTO {

    //here we pot the necessary objects for thymeleaf
    protected String title;

    protected String renderedContent;

    protected String renderedSummary;

    protected Date publishAt;

    protected String imgUrl;

    protected String url;

    protected Category category;

    protected Author author;

    protected TimeToRead timeToRead;

    public PostDTO(String title,
                   String renderedContent,
                   String renderedSummary,
                   Date publishAt,
                   String imgUrl,
                   String url,
                   Category category,
                   Author author,
                   TimeToRead timeToRead
                   ){
        this.title = title;
        this.renderedContent = renderedContent;
        this.renderedSummary = renderedSummary;
        this.publishAt = publishAt;
        this.imgUrl = imgUrl;
        this.url = url;
        this.category = category;
        this.author = author;
        this.timeToRead = timeToRead;
    }

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public TimeToRead getTimeToRead() {
        return timeToRead;
    }

    public void setTimeToRead(TimeToRead timeToRead) {
        this.timeToRead = timeToRead;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRenderedContent() {
        return renderedContent;
    }

    public void setRenderedContent(String renderedContent) {
        this.renderedContent = renderedContent;
    }

    public String getRenderedSummary() {
        return renderedSummary;
    }

    public void setRenderedSummary(String renderedSummary) {
        this.renderedSummary = renderedSummary;
    }

    public Date getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(Date publishAt) {
        this.publishAt = publishAt;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}
