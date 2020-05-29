package com.backend.fluxnewsapi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode
@ToString
@Entity(name = "users")
public class User {
    public User(){};
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String username;
    private String password;
    @Column(name = "connectionstatus")
    private Boolean connectStatus;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Initialisation misajour;

    @OneToMany(mappedBy = "user")
    private Set<ArticleUser> isRead;
    @OneToMany(mappedBy = "user")
    private Set<ArticleUser> isSave;
    @OneToMany(mappedBy = "user")
    private Set<ArticleUser> isNote;
    @OneToMany(mappedBy = "user")
    private Set<ArticleUser> isLike;
    @OneToMany(mappedBy = "user")
    private Set<ArticleUser> liker;
}