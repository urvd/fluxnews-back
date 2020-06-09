package com.backend.fluxnewsapi.dtos.models;

import com.backend.fluxnewsapi.models.Article;
import com.backend.fluxnewsapi.models.ArticleUser;
import com.backend.fluxnewsapi.models.User;

import java.util.ArrayList;
import java.util.List;

public class ArticleUserBuilder {

    private static ArticleUser build(User user, Article article){
        return new ArticleUser(user,article);
    }

    public static List<ArticleUser> withArticlesOfUser(User user, List<Article> articles){
        List<ArticleUser> nRefUserArticles = new ArrayList<>();
        for(int i = 0; i < articles.size(); i++){
            nRefUserArticles.add(build(user,articles.get(i)));
        }
        return nRefUserArticles;
    }
}
