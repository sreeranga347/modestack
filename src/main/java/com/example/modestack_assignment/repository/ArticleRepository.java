package com.example.modestack_assignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.modestack_assignment.entity.Article;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
	
	

}
