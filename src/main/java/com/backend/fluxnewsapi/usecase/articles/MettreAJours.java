package com.backend.fluxnewsapi.usecase.articles;

import com.backend.fluxnewsapi.domain.dtos.EntityDtoMap;
import com.backend.fluxnewsapi.domain.dtos.models.ArticleDto;
import com.backend.fluxnewsapi.domain.dtos.models.Articlesfetched;
import com.backend.fluxnewsapi.domain.exceptions.MyMappingException;
import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.domain.utils.ErrorCode;
import com.backend.fluxnewsapi.infrastucture.models.Article;
import com.backend.fluxnewsapi.infrastucture.models.User;
import com.backend.fluxnewsapi.services.ArticleStoreService;
import com.backend.fluxnewsapi.services.UserArticlesService;
import com.backend.fluxnewsapi.services.UserServices;
import com.backend.fluxnewsapi.services.newsapi.NewsApiFetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component("articlesDuJours")
public class MettreAJours {

    private ArticleStoreService articleStoreService;

    private UserServices userService;
    @Autowired
    public MettreAJours(ArticleStoreService articleStoreService, UserArticlesService userArticlesService) {
        this.articleStoreService = articleStoreService;
        this.userService = userService;
    }

    public List<ArticleDto> fetch() throws IOException, MyMappingException, RessourceException {

        //List<UserArticleDTO> dtos = new ArrayList<>();
        NewsApiFetch apiFetch = new NewsApiFetch();
        Articlesfetched articlesFetched = apiFetch.fetchArticles();
        /**
         * Delete old articles
         */
        for(int i = 1; i <= articlesFetched.getTotalResults(); i++){
            if( articleStoreService.isArticlesWithRef(i) ){
                articleStoreService.cleanArticleStoreWithRef(i);
            }
            /**
             * prepare return statement
             */
            //dtos.add(new UserArticleDTO(articlesFetched.getArticles().get(i)));*/
        }

        /**
         * save or update new article
         */
        EntityDtoMap<Article, ArticleDto> entityDtoMapArticleOfTheDay = new EntityDtoMap();
        List<Article> articleIsSets;
        articleIsSets = entityDtoMapArticleOfTheDay.convertToEntity(
                articlesFetched.getArticles(),
                Article.class);

        if(!articleStoreService.storeNewArticles(articleIsSets)){
            throw new RessourceException(ErrorCode.UNSAVED);
        }
        return articlesFetched.getArticles();
    }

    public List<ArticleDto> fetch(Long userid) throws IOException, MyMappingException, RessourceException {
        NewsApiFetch apiFetch = new NewsApiFetch();
        Articlesfetched articlesFetched = apiFetch.fetchArticles();
        /**
         * Delete old articles
         */
        for(int i = 1; i <= articlesFetched.getTotalResults(); i++){
            if( articleStoreService.isArticlesWithRef(i) ){
                articleStoreService.cleanArticleStoreWithRef(i);
            }
        }

        /**
         * save or update new article
         */
        EntityDtoMap<Article, ArticleDto> entityDtoMapArticleOfTheDay = new EntityDtoMap();
        List<Article> articleIsSets;
        articleIsSets = entityDtoMapArticleOfTheDay.convertToEntity(
                articlesFetched.getArticles(),
                Article.class);

        if(!articleStoreService.storeNewArticles(articleIsSets)){
            throw new RessourceException(ErrorCode.UNSAVED);
        }

        /**
         * save or update refs in other entity
         */
        User user = userService.findUser(userid);
        for( int i = 0; i < 20; i++){
            user.getArticleUsers().get(i).setArticle(articleIsSets.get(i));
        }

        return articlesFetched.getArticles();
    }
}
