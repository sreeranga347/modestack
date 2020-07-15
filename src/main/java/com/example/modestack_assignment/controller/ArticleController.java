package com.example.modestack_assignment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.modestack_assignment.dto.ArticleDto;
import com.example.modestack_assignment.entity.Article;
import com.example.modestack_assignment.model.request.ArticleRequestModel;
import com.example.modestack_assignment.service.ArticleService;

@RestController
@RequestMapping("/articles")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@GetMapping()
	public List<ArticleRequestModel> getUsers(@RequestParam(value = "page",defaultValue = "0") int page,
			@RequestParam(value = "limit",defaultValue = "20") int limit) {
		List<ArticleRequestModel> articleRequestModels=new ArrayList<ArticleRequestModel>();
		List<ArticleDto> articles=articleService.getArticles(page,limit);	
		for(ArticleDto articleDto:articles) {
			ArticleRequestModel model=new ArticleRequestModel();
			BeanUtils.copyProperties(articleDto, model);
			articleRequestModels.add(model);
		}
		return articleRequestModels;
	}
	
	@PostMapping
	public String createArticle(@RequestBody ArticleRequestModel articleRequestModel) {
		
		ArticleDto articleDto=new ArticleDto();
		BeanUtils.copyProperties(articleRequestModel, articleDto);
		articleService.createArticle(articleDto);
		return "Article Created";
	}

}
