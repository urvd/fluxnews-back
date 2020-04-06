package com.backend.fluxnewsapi.routes;

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

    @GetMapping
    public HashMap<String,Object> getArticle(){
        return null;
    }
    @GetMapping("/savelist")
    public HashMap<String,Object> getArticleALirePlusTard(){
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

