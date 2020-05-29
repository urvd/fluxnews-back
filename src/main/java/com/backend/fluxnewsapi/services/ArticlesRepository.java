package com.backend.fluxnewsapi.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.fluxnewsapi.models.Article;

public interface ArticlesRepository  extends JpaRepository<Article, Long> {

    @Query(
            value =
                    "insert into Users (name, age, email, status) values (:id, :author, :source, :url," +
                            ":url)",
            nativeQuery = true)
    void insertAll();
}
