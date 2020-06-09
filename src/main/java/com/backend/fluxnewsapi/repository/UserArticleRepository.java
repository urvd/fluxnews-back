package com.backend.fluxnewsapi.repository;

import com.backend.fluxnewsapi.models.ArticleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserArticleRepository extends JpaRepository<ArticleUser, Long> {
}
