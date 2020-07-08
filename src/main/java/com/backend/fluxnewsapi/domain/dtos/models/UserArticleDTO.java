package com.backend.fluxnewsapi.domain.dtos.models;

import lombok.Data;

@Data
public class UserArticleDTO {
    public UserArticleDTO(ArticleDto article) {
        this.article = article;
        this.like = false;
        this.note = 0;
    }

    ArticleDto article;
    boolean like;
    int note;
}
