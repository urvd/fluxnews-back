package com.backend.fluxnewsapi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode
@Entity(name = "users")
public class User {
    public User(){};
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private String username;
    private String password;
    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<ArticleUser> articlesUser;
}