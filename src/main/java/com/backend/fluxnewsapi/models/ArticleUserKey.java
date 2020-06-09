package com.backend.fluxnewsapi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
public class ArticleUserKey implements Serializable {
    @Column(name = "user_id")
    private long userId;
    @Column(name = "article_id")
    private long articleId;
}
