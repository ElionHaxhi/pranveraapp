package com.pranveraapp.core.post;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


import com.pranveraapp.common.support.DateFactory;
import com.pranveraapp.core.post.domain.Post;


public final class PostView {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMMM dd, yyyy");
	
	private final Post post;
    private final DateFactory dateFactory;

    public PostView(Post post){
        this.post = post;
        this.dateFactory = null;
    }

    private PostView(Post post, DateFactory dateFactory) {
        this.post = post;
        this.dateFactory = dateFactory;
    }
    
    public static PostView of(Post post, DateFactory dateFactory) {
        return new PostView(post, dateFactory);
    }
    
   /* public static Page<PostView> pageOf(Page<Post> posts, DateFactory dateFactory) {
        List<PostView> postViews = posts.getContent().stream()
                .map(post -> of(post, dateFactory))
                .collect(Collectors.toList());
        PageRequest pageRequest = new PageRequest(posts.getNumber(), posts.getSize(), posts.getSort());
        return new PageImpl<>(postViews, pageRequest, posts.getTotalElements());
    }*/
    
    /*public String getPath() {
        return "/post/" +post.getId()+"/"+ post.getPublicSlug();
    }*/
    
    public String getDay(){
    	int day=post.getPublishAt().getDate();
    	return new Integer(day).toString();
    }
    
    public String getMonth(){
    	int month=post.getPublishAt().getMonth();
    	return new Integer(month).toString();
    }
    
    public String getTitle() {
        return post.getTitle();
    }
    
    public boolean showReadMore() {
        return !post.getRenderedContent().equals(post.getRenderedSummary());
    }
    
    public String getRenderedSummary() {
        return post.getRenderedSummary();
    }
    
    public String getRenderedContent() {
        return post.getRenderedContent();
    }

    public Date getPublishAt() {
        return post.getPublishAt();
    }
    
    public String getUrlimg(){
    	return post.getImgUrl();
    }
    
}
