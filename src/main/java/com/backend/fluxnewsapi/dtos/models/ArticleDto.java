package com.backend.fluxnewsapi.dtos.models;

import com.backend.fluxnewsapi.models.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ArticleDto {
    public ArticleDto(){}
    public ArticleDto(String source, String author, String title, String description, String url, String urlImage, String publishedAt, String content) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlImage = urlImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    private int id;
    private String source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlImage;
    private String publishedAt;
    private String content;
}
