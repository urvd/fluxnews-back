package com.backend.fluxnewsapi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Entity(name = "articlesuser")
public class ArticleUser implements Serializable {

    public ArticleUser(User user) {
        this.user = user;
        this.isLike = false;
        this.isNote = false;
        this.isRead = false;
        this.isSave = false;
        this.noter = 0;
    }
    @EmbeddedId
    private ArticleUserKey keyId;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userid")
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("articleid")
    @JoinColumn(name = "articleid")
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
