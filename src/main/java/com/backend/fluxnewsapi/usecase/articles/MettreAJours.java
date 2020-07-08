package com.backend.fluxnewsapi.usecase.articles;

import com.backend.fluxnewsapi.domain.dtos.EntityDtoMap;
import com.backend.fluxnewsapi.domain.dtos.models.ArticleDto;
import com.backend.fluxnewsapi.domain.dtos.models.Articlesfetched;
import com.backend.fluxnewsapi.domain.exceptions.MyMappingException;
import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.domain.utils.ErrorCode;
import com.backend.fluxnewsapi.infrastucture.models.Article;
import com.backend.fluxnewsapi.services.ArticleStoreService;
import com.backend.fluxnewsapi.services.UserArticlesService;
import com.backend.fluxnewsapi.services.newsapi.NewsApiFetch;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class MettreAJours {
    @Autowired
    private ArticleStoreService storeService;
    @Autowired
    private UserArticlesService userArticlesService;

    public List<ArticleDto> fetch() throws IOException, MyMappingException, RessourceException {

        //List<UserArticleDTO> dtos = new ArrayList<>();
        NewsApiFetch apiFetch = new NewsApiFetch();
        Articlesfetched articlesFetched = apiFetch.fetchArticles();
        /**
         * Delete old articles
         */
        for(int i = 1; i <= articlesFetched.getTotalResults(); i++){
            if( storeService.isArticlesWithRef( i ) ){
                storeService.cleanArticleStoreWithRef(i);
            }
            /**
             * prepare return statement
             *//*
            dtos.add(new UserArticleDTO(articlesFetched.getArticles().get(i)));*/
        }

        /**
         * save or update new article
         */
        EntityDtoMap<Article, ArticleDto> entityDtoMapArticleOfTheDay = new EntityDtoMap();
        List<Article> articleIsSets;
        articleIsSets = entityDtoMapArticleOfTheDay.convertToEntity(
                articlesFetched.getArticles(),
                Article.class);

        if(!storeService.storeNewArticles(articleIsSets)){
            throw new RessourceException(ErrorCode.UNSAVED);
        }
        return articlesFetched.getArticles();
        /**
         * save or update refs in other entity
         */
        /*List<Article> store =  getArticlesStore();
        List<ArticleUser> list = ArticleUserBuilder.withArticlesOfUser(user,store);
        /*for(ArticleUser ua : list){
            userArticleRepository.save(ua);
        }
        userArticleRepository.saveAll(list)*/
    }
}
