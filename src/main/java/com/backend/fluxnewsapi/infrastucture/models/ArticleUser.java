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
    public ArticleUser(){ }

    public ArticleUser(User user) {
        this.user = user;
    }

    public ArticleUser(User user, Article article) {
        this.user = user;
        this.article = article;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleid")
    private Article article;

    @Column(name = "isread",columnDefinition = "boolean default false")
    private boolean isRead;
    @Column(name = "isnote",columnDefinition = "boolean default false")
    private boolean isNote;
    @Column(name = "islike",columnDefinition = "boolean default false")
    private boolean isLike;
    private boolean liker;
    @Max(5)
    private int noter;
}
