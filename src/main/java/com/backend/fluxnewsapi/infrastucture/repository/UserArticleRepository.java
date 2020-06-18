package com.backend.fluxnewsapi.infrastucture.repository;

import com.backend.fluxnewsapi.infrastucture.models.ArticleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserArticleRepository extends JpaRepository<ArticleUser, Long> {
}
