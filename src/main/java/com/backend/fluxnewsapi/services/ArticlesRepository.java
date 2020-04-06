package com.backend.fluxnewsapi.services;

import com.backend.fluxnewsapi.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlesRepository  extends JpaRepository<Article, Integer> {

}
