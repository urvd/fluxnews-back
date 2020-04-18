package com.backend.fluxnewsapi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@EqualsAndHashCode
//@Embeddable
public class ArticleUserKey implements Serializable {
    @Column(name = "userid")
    private int userId;
    @Column(name = "articleid")
    private int articleId;
}
