package com.example.modestack_assignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class Article {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private long id;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="BODY")
	private String body;
	
	@Column(name="AUTHOR")
	private String author;
	
	public Article() {
		
	}

	public Article(String title, String body, String author) {
		this.title = title;
		this.body = body;
		this.author = author;
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

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", body=" + body + ", author=" + author + "]";
	}
	
	
	
	

}
