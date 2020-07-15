package com.example.modestack_assignment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.modestack_assignment.dto.ArticleDto;
import com.example.modestack_assignment.entity.Article;
import com.example.modestack_assignment.repository.ArticleRepository;
import com.example.modestack_assignment.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public ArticleDto createArticle(ArticleDto articleDto) {
		Article article=new Article();
		BeanUtils.copyProperties(articleDto, article);
		articleRepository.save(article);
		return articleDto;
	}

	@Override
	public List<ArticleDto> getArticles(int page,int limit) {
		// TODO Auto-generated method stub
		Pageable pageable=PageRequest.of(page, limit);
		Page<Article> pageArticles= articleRepository.findAll(pageable);
		List<Article> articles=pageArticles.getContent();
		List<ArticleDto> articleDtos=new ArrayList<ArticleDto>();
		for(Article  article:articles) {
			ArticleDto articleDto=new ArticleDto();
			BeanUtils.copyProperties(article, articleDto);
			articleDtos.add(articleDto);
		}
		return articleDtos;
	}

	
	
	

}
