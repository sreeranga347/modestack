package com.example.modestack_assignment.service;

import java.util.List;

import com.example.modestack_assignment.dto.ArticleDto;
import com.example.modestack_assignment.entity.Article;

public interface ArticleService {

	 ArticleDto createArticle(ArticleDto articleDto);
	
	 List<ArticleDto> getArticles(int page,int limit);
}
