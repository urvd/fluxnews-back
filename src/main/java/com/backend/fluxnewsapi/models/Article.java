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
    private long id;
    private String source;
    private String author;
    private String title;
    //@Column(name = "description",length = 1024)
    @Lob
    private String description;
    @Lob
    private String url;
    @Lob
    @Column(name = "urlimage")
    private String urlImage;
    @Column(name = "publishedat")
    private String publishedAt;
    //@Column(name = "content",length = 65025)
    @Lob
    private String content;
    @OneToMany(mappedBy = "article")
    private Set<ArticleUser> isRead;
    @OneToMany(mappedBy = "article")
    private Set<ArticleUser> isSave;
    @OneToMany(mappedBy = "article")
    private Set<ArticleUser> isNote;
    @OneToMany(mappedBy = "article")
    private Set<ArticleUser> isLike;
    @OneToMany(mappedBy = "article")
    private Set<ArticleUser> liker;
}
