package com.backend.fluxnewsapi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private String prenom;
    private String username;
    private String password;
    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<ArticleUser> articlesUser;
}