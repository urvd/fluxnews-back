package com.backend.fluxnewsapi.infrastucture.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Entity(name = "articlesuser")
public class ArticleUser implements Serializable {

    public ArticleUser(User user, Article article) {
        this.user = user;
        this.article = article;
        this.isLike = false;
        this.isNote = false;
        this.isRead = false;
        this.isSave = false;
        this.noter = 0;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity=User.class)
    //@MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Article.class)
    //@MapsId("article_id")
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name = "isread")
    private boolean isRead;
    @Column(name = "issave")
    private boolean isSave;
    @Column(name = "isnote")
    private boolean isNote;
    @Column(name = "islike")
    private boolean isLike;
    private boolean liker;
    @Max(5)
    private int noter;
}
