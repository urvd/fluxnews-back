package com.backend.fluxnewsapi.infrastucture.controllers;

import com.backend.fluxnewsapi.domain.dtos.EntityDtoMap;
import com.backend.fluxnewsapi.domain.dtos.models.ArticleDto;
import com.backend.fluxnewsapi.domain.dtos.models.Articlesfetched;
import com.backend.fluxnewsapi.domain.exceptions.MyMappingException;
import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.infrastucture.models.Article;
import com.backend.fluxnewsapi.infrastucture.models.Initialisation;
import com.backend.fluxnewsapi.infrastucture.models.User;
import com.backend.fluxnewsapi.infrastucture.repository.ArticlesRepository;
import com.backend.fluxnewsapi.infrastucture.repository.InitialisationRepository;
import com.backend.fluxnewsapi.infrastucture.repository.UserArticleRepository;
import com.backend.fluxnewsapi.infrastucture.repository.UsersRepository;
import com.backend.fluxnewsapi.services.newsapi.NewsApiFetch;
import com.backend.fluxnewsapi.domain.utils.DateUtils;
import com.backend.fluxnewsapi.domain.utils.ErrorCode;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticlesRessourceController {

    private UsersRepository usersRepository;

    private ArticlesRepository articlesRepository;
    private InitialisationRepository initRepository;
    private UserArticleRepository userArticleRepository;
    public ArticlesRessourceController(UsersRepository usersRepository, ArticlesRepository articlesRepository,
                                       InitialisationRepository initRepository, UserArticleRepository userArticleRepository){
        this.usersRepository = usersRepository;
        this.articlesRepository = articlesRepository;
        this.initRepository = initRepository;
        this.userArticleRepository = userArticleRepository;
    }
    private  List<Article> getArticlesStore(){
        return  articlesRepository.findAll();
    }
    private Optional<Article> getArticleFromStore(Long id){
        return articlesRepository.findById(id);
    }

/*    @GetMapping("/json")
    public String jsonApi() throws IOException {
        return  new NewsApiFetch().runArticles();
    }*/

    @GetMapping("/{userid}")
    public ResponseEntity<List<ArticleDto>> getArticles(@PathVariable(value = "userid") Long userid) throws JSONException, MyMappingException, IOException, ClassNotFoundException, RessourceException {

        User u = usersRepository.findById(userid)
                .orElseThrow(() -> new RessourceException(ErrorCode.NOT_FOUND));

        EntityDtoMap<Article, ArticleDto> entityDtoMapArticleOfTheDay = new EntityDtoMap();
        if(haveMiseAJours(userid)){
            fetchArticles(u);
            return ResponseEntity.ok().body(entityDtoMapArticleOfTheDay
                    .convertToDto(getArticlesStore(), ArticleDto.class));
        }
        return ResponseEntity.ok().body(entityDtoMapArticleOfTheDay
                    .convertToDto(getArticlesStore(), ArticleDto.class));

    }
    @GetMapping("/savelist")
    public List<ArticleDto> getArticleALirePlusTard(){
        //usersRepository.
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

    /**
     * privates methods
     */

    private boolean haveMiseAJours(long userid) throws IOException, MyMappingException, ClassNotFoundException, RessourceException, JSONException {

        Initialisation initParam = initRepository.findByUserId(userid);
        if(initParam.getUpdateday().equals(DateUtils.today()) && initParam.isToInitied()){
            initParam.setToInitied(false);
            initParam.setUpdateday(DateUtils.tomorrow());
            initRepository.save(initParam);
            return true;
        }
        return false;
    }

    private void fetchArticles(User user) throws IOException, JSONException, MyMappingException {
        NewsApiFetch apiFetch = new NewsApiFetch();
        Articlesfetched articlesFetched = apiFetch.fetchArticles();
        /**
         * Delete old articles
        */
        for(int i = 1; i <= articlesFetched.getTotalResults(); i++){
            if(articlesRepository.existsById(Long.valueOf(i))){
                articlesRepository.delete(getArticleFromStore(Long.valueOf(i)).get());
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

        articlesRepository.saveAll(articleIsSets);
        /**
         * save or update refs in other entity
         */
        /*List<Article> store =  getArticlesStore();
        List<ArticleUser> list = ArticleUserBuilder.withArticlesOfUser(user,store);
        /*for(ArticleUser ua : list){
            userArticleRepository.save(ua);
        }
        userArticleRepository.saveAll(list)*/;
    }
}