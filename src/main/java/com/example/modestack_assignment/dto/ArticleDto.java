package com.example.modestack_assignment.dto;

import java.io.Serializable;

public class ArticleDto implements Serializable {
	
	private long id;
	private String title;
	private String body;
	private String author;
	
	
	public ArticleDto() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	

}
