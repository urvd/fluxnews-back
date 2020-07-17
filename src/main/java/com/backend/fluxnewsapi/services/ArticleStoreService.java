package com.backend.fluxnewsapi.services;

import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.domain.exceptions.ErrorCode;
import com.backend.fluxnewsapi.infrastucture.models.Article;
import com.backend.fluxnewsapi.infrastucture.models.ArticleUser;
import com.backend.fluxnewsapi.infrastucture.repository.ArticlesRepository;
import com.backend.fluxnewsapi.infrastucture.repository.UserArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("articleStoreService")
public class ArticleStoreService {

    private UserArticleRepository userArticleRepository;

    private ArticlesRepository articlesRepository;

    @Autowired
    public ArticleStoreService(ArticlesRepository articlesRepository, UserArticleRepository userArticleRepository) {
        this.articlesRepository = articlesRepository;
        this.userArticleRepository = userArticleRepository;
    }
    /**
     * search and find services
     */
    public List<Article> findArticlesStore(){
        return articlesRepository.findAll();
    }

    public Article findArticlesWithRef(int id){
        return articlesRepository.findByNumberid(id);
    }
    public boolean isArticlesWithRef(int id){
        boolean test = articlesRepository.existsByNumberid(id);
        return test;
    }

    public List<Article> getArticleUser(Long userid) throws RessourceException {
        List<ArticleUser> userArticles = userArticleRepository.findByUserId(userid);
        if(userArticles == null ){
            throw new RessourceException(ErrorCode.NOT_FOUND);
        }
        List<Article> articles = new ArrayList<>();
        for(ArticleUser ua: userArticles){
            articles.add(ua.getArticle());
        }
        return articles;
    }

    /**
     * Delete services
     */
    public void cleanFromArticleStore(Article article){
        articlesRepository.delete(article);
    }
    public void cleanArticleStoreWithRef(int id){
        Article art = articlesRepository.findByNumberid(id);
        articlesRepository.delete(art);
    }

    /**
     * Save service
     */
    public boolean storeNewArticles(List<Article> articles){
        try{
            articlesRepository.saveAll(articles);
            return true;
        }catch(Exception e){
            return false;
        }
    }

}
