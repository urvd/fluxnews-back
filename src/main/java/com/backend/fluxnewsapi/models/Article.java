package com.backend.fluxnewsapi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Data
@EqualsAndHashCode
@Entity(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String author;
    private String contenu;
    private String datePublication;
    private String description;
    private String source;
    private String tilte;
    private String urlImage;
    private String urlArticle;
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    Set<ArticleUser> articleUser;
}
