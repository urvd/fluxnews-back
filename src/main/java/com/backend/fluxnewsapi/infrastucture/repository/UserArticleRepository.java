package com.backend.fluxnewsapi.infrastucture.repository;

import com.backend.fluxnewsapi.infrastucture.models.ArticleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserArticleRepository extends JpaRepository<ArticleUser, Long> {
    List<ArticleUser> findByUserId(long userId);
    ArticleUser findByUserIdAndArticleId(long userId, long articleId);
}
