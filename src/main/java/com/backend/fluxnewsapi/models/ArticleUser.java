package com.backend.fluxnewsapi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Set;

@Data
@EqualsAndHashCode
@Entity
public final  class ArticleUser {
/*    @EmbeddedId
    private ArticleUserKey keyId;*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userid")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("articleid")
    Article article;

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
