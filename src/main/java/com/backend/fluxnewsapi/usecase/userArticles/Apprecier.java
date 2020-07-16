package com.backend.fluxnewsapi.usecase.userArticles;

import com.backend.fluxnewsapi.domain.dtos.models.Appreciation;
import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.infrastucture.models.ArticleUser;
import com.backend.fluxnewsapi.services.UserArticlesService;
import com.backend.fluxnewsapi.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;

public class Apprecier {
    @Autowired
    private UserArticlesService userArticlesService;
    @Autowired
    private UserServices userService;

    public Appreciation getCurrent(Long userid, Long articleid) throws RessourceException {
        ArticleUser articleUser = userArticlesService.getArticleUserWith(userid,articleid);
        Appreciation appreciation = new Appreciation(userid,articleid);
        appreciation.setLu(articleUser.isRead());
        appreciation.setAimer(articleUser.isLiker());
        appreciation.setNote(articleUser.getNoter());
        return  appreciation;
    }

    public Boolean add(Appreciation appr) throws RessourceException {
        ArticleUser articleUser = userArticlesService.getArticleUserWith(appr.getUserid(),appr.getRefId());
/*        articleUser.set
        User user = userService.findUser(useid);*/
        articleUser.setRead(true);
        articleUser.setNote(articleUser.isNote()?true:false);
        articleUser.setLike(appr.isAimer()?true:false);

        articleUser.setLiker(appr.isAimer());
        articleUser.setNoter(appr.getNote());

        return userArticlesService.save(articleUser) ;
    }

    public Boolean modify(Appreciation appr) throws RessourceException {
        ArticleUser articleUser = userArticlesService.getArticleUserWith(appr.getUserid(), appr.getRefId());
/*        articleUser.set
        User user = userService.findUser(useid);*/
        articleUser.setNote(articleUser.isNote()?true:false);
        articleUser.setLike(appr.isAimer()?true:false);

        articleUser.setLiker(appr.isAimer());
        articleUser.setNoter(appr.getNote());

        return userArticlesService.save(articleUser) ;
    }
}