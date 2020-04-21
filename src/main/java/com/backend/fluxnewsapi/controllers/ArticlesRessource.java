package com.backend.fluxnewsapi.controllers;

import com.backend.fluxnewsapi.services.ArticlesRepository;
import com.backend.fluxnewsapi.services.UsersRepository;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@RequestMapping("/articles")
public class ArticlesRessource {

    private UsersRepository usersRepository;

    private ArticlesRepository articlesRepository;
    public ArticlesRessource(UsersRepository usersRepository, ArticlesRepository articlesRepository){
        this.usersRepository = usersRepository;
        this.articlesRepository = articlesRepository;
    }

    /**
     * OBJECT: ArticleSaveLater => id article, idUser, save (boolean)
     * OBJECT: ArticleLU => id article, idUser, save (boolean)
     * @return
     */

    @GetMapping("/all")
    public List<ArticleDto> getArticle(){
        /**
         * set new Article() from api data throught ArticleDTO mapping and do it once a day
         * return Article dto from article (store in db)
         */
    	Script sjs = Script.getInstance();
        //sjs.runJavascript();
        return (String) sjs.helloJavascript();
    	
        return null;
    }
    @GetMapping("/savelist")
    public List<ArticleDto> getArticleALirePlusTard(){
        return null;
    }
    @PutMapping("/savelist/{id}")
    public HashMap<String,Object> addArticleALirePlusTard(@PathVariable(value = "id") Long id){
        return null;
    }

    @DeleteMapping("/savelist/{id}")
    public HashMap<String, Object> removeArticleALirePlusTard(@PathVariable(value = "id") Long id){
        return null;
    }
}

