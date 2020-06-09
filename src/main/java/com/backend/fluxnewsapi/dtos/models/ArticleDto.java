package com.backend.fluxnewsapi.dtos.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.lang.Nullable;

@Data
@EqualsAndHashCode
public class ArticleDto {
    public ArticleDto(){}
    public ArticleDto(@Nullable String source, @Nullable String author, @Nullable String title,
                      @Nullable String description, @Nullable String url, @Nullable String urlImage,
                      @Nullable String publishedAt, @Nullable String content) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlImage = urlImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    private int numberid;
    //private Source source;
    private String source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlImage;
    private String publishedAt;
    private String content;
}
