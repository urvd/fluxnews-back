package com.backend.fluxnewsapi.services;

import com.backend.fluxnewsapi.models.ArticleUser;
import com.backend.fluxnewsapi.models.ArticleUserKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserArticleRepository extends JpaRepository<ArticleUser, ArticleUserKey> {
}
