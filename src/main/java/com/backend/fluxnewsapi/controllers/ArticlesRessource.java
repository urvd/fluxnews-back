package com.backend.fluxnewsapi.controllers;

import com.backend.fluxnewsapi.models.*;
import com.backend.fluxnewsapi.services.UserArticleRepository;
import com.backend.fluxnewsapi.traitrements.NewsApiFetch;
import com.backend.fluxnewsapi.dtos.EntityDtoMap;
import com.backend.fluxnewsapi.dtos.models.ArticleDto;
import com.backend.fluxnewsapi.dtos.models.Articlesfetched;
import com.backend.fluxnewsapi.exceptions.MyMappingException;
import com.backend.fluxnewsapi.exceptions.RessourceException;
import com.backend.fluxnewsapi.services.ArticlesRepository;
import com.backend.fluxnewsapi.services.InitialisationRepository;
import com.backend.fluxnewsapi.services.UsersRepository;
import com.backend.fluxnewsapi.utils.DateUtils;
import com.backend.fluxnewsapi.utils.ErrorCode;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticlesRessource {

    private UsersRepository usersRepository;

    private ArticlesRepository articlesRepository;
    private InitialisationRepository initRepository;
    private UserArticleRepository userArticleRepository;
    public ArticlesRessource(UsersRepository usersRepository, ArticlesRepository articlesRepository,
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

    @GetMapping("/all/{userid}")
    public ResponseEntity<List<ArticleDto>> getArticles(@PathVariable(value = "userid") Long userid) throws JSONException, MyMappingException, IOException, ClassNotFoundException, RessourceException {

        Optional<User> u = usersRepository.findById(userid);
        Initialisation searchfor = new Initialisation();
        searchfor.setUser(u.get());
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<Initialisation> example = Example.of(searchfor, matcher);
        if(!initRepository.exists(example)){
            throw new RessourceException(ErrorCode.NOT_FOUND);
        }

        EntityDtoMap<Article, ArticleDto> entityDtoMapArticleOfTheDay = new EntityDtoMap();
        if(haveMiseAJours(userid)){
            fetchArticles(u.get());
            return ResponseEntity.ok().body(entityDtoMapArticleOfTheDay
                    .convertToDto(getArticlesStore(), ArticleDto.class));
        }
        return ResponseEntity.ok().body(entityDtoMapArticleOfTheDay
                    .convertToDto(getArticlesStore(), ArticleDto.class));

    }
    @GetMapping("/savelist")
    @ResponseBody
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
     * privates methodes
     */

    private boolean haveMiseAJours(long userid) throws IOException, MyMappingException, ClassNotFoundException, RessourceException, JSONException {

        Boolean update = false;
        Initialisation initParam = initRepository.findByUserId(userid);
        if(initParam.getUpdateday().equals(DateUtils.today()) && !initParam.isToInitied()){

            initParam.setToInitied(true);
            update = true;
        }else {
            return update;
        }
        if(initParam.getUpdateday().equals(DateUtils.today())
                && (update || initParam.isToInitied())) {
            initParam.setUpdateday(DateUtils.tomorrow());
            initRepository.save(initParam);
            return true;
        }
        return false;
    }

    private void fetchArticles(User user) throws IOException, JSONException, MyMappingException {
        NewsApiFetch apiFetch = new NewsApiFetch();
        List<Article> articleIsSets = new ArrayList<>();
        EntityDtoMap<Article, ArticleDto> entityDtoMapArticleOfTheDay = new EntityDtoMap();
        Articlesfetched articlesFetched = apiFetch.fetchArticles();

        for(int i=0; i<articlesFetched.getTotalResults(); i++){
            if(articlesRepository.existsById(Long.valueOf(i))){
                articlesRepository.delete(getArticleFromStore(Long.valueOf(i)).get());
            }
            //ArticleUserKey Key
            //ArticleUser ua:
        }
        articleIsSets = entityDtoMapArticleOfTheDay.convertToEntity(
                articlesFetched.getArticles(),
                Article.class);
        articlesRepository.saveAll(articleIsSets);
    }
}