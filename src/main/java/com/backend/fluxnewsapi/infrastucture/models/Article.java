package com.backend.fluxnewsapi.infrastucture.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;


@Data
@EqualsAndHashCode
@Entity(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int numberid;
    private String source;
    private String author;
    @Lob
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
    @Lob
    private String content;

    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ArticleUser> articleUsers;

/*    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<ArticleUser> isRead;
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<ArticleUser> isNote;
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<ArticleUser> isLike;
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<ArticleUser> liker;
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ArticleUser> noter;*/
}
