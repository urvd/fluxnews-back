package com.backend.fluxnewsapi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;


@Data
@EqualsAndHashCode
@Entity(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String source;
    private String author;
    private String title;
    //@Column(name = "description",length = 1024)
    @Lob
    private String description;
    @Column(length = 1024)
    private String url;
    @Column(length = 1024)
    private String urlImage;
    private String publishedAt;
    //@Column(name = "content",length = 65025)
    @Lob
    private String content;
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    Set<ArticleUser> articleUser;
}
