package com.backend.fluxnewsapi.services;

import com.backend.fluxnewsapi.domain.exceptions.ErrorCode;
import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.infrastucture.models.ArticleUser;
import com.backend.fluxnewsapi.infrastucture.repository.UserArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userArticlesService")
public class UserArticlesService {

    private UserArticleRepository userArticleRepository;

    @Autowired
    public UserArticlesService(UserArticleRepository userArticleRepository) {
        this.userArticleRepository = userArticleRepository;
    }

    public List<ArticleUser> getArticlesUser(Long userid) throws RessourceException {
        List<ArticleUser> userArticles = userArticleRepository.findByUserId(userid);
        if(userArticles == null || userArticles.isEmpty()){
            throw new RessourceException(ErrorCode.NOT_FOUND);
        }
        return userArticles;
    }

    public ArticleUser getArticleUserWith(Long userid, Long refId) throws RessourceException {
        ArticleUser userArticle = userArticleRepository.findByUserIdAndArticleId(userid,refId);
        if(userArticle == null ){
            throw new RessourceException(ErrorCode.NOT_FOUND);
        }
        return userArticle;
        /*List<Article> articles = new ArrayList<>();
        for(ArticleUser ua: userArticles){
            articles.add(ua.getArticle());
        }
        return articles;*/
    }

    public boolean save(ArticleUser articleUser) {
        try{
            return userArticleRepository.save(articleUser) != null;
        }catch (Exception e){
            return false;
        }
    }
}
