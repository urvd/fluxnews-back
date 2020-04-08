package com.backend.fluxnewsapi.dtos.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ArticleDto {
    private int id;
    private String author;
    private String contenu;
    private String datePublication;
    private String description;
    private String source;
    private String tilte;
    private String urlImage;
    private String urlArticle;
}
