package com.backend.fluxnewsapi.dtos.models;

import com.backend.fluxnewsapi.models.ArticleUser;
import com.backend.fluxnewsapi.models.User;

import java.util.ArrayList;
import java.util.List;

public class ArticleUserBuilder {

    public static ArticleUser withUser(User user){
        return new ArticleUser(user);
    }

    public static List<ArticleUser> withUser(User user, int nbRef){
        List<ArticleUser> nRefUserArticles = new ArrayList<>();
        for(int i = 0; i < nbRef; i++){
            nRefUserArticles.add(withUser(user));
        }
        return nRefUserArticles;
    }
}
