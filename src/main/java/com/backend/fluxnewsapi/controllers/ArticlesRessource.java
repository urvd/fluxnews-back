package com.backend.fluxnewsapi.controllers;

import com.backend.fluxnewsapi.config.NewsApiFetch;
import com.backend.fluxnewsapi.dtos.EntityDtoMap;
import com.backend.fluxnewsapi.dtos.models.ArticleDto;
import com.backend.fluxnewsapi.dtos.models.Articlesfetched;
import com.backend.fluxnewsapi.models.Article;
import com.backend.fluxnewsapi.models.Initialisation;
import com.backend.fluxnewsapi.services.ArticlesRepository;
import com.backend.fluxnewsapi.services.InitialisationRepository;
import com.backend.fluxnewsapi.services.UsersRepository;
import com.backend.fluxnewsapi.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticlesRessource {

    private UsersRepository usersRepository;

    private ArticlesRepository articlesRepository;
    private InitialisationRepository initRepository;
    public ArticlesRessource(UsersRepository usersRepository, ArticlesRepository articlesRepository,InitialisationRepository initRepository){
        this.usersRepository = usersRepository;
        this.articlesRepository = articlesRepository;
        this.initRepository = initRepository;
    }

    /**
     * OBJECT: ArticleSaveLater => id article, idUser, save (boolean)
     * OBJECT: ArticleLU => id article, idUser, save (boolean)
     * @return
     */

    @GetMapping
    public ResponseEntity<List<ArticleDto>> getArticles() throws IOException {

        List<ArticleDto> articleDtos;
        EntityDtoMap<Article, ArticleDto> entityDtoMapArticleOfTheDay = new EntityDtoMap();
    	try{

            if(InitilisationRessource.checkNextInitDay() && !InitilisationRessource.checkToInitData()){
                Initialisation initParam =  new Initialisation();
                initParam.setToInitied(true);
                InitilisationRessource.updateInitParam(initParam);
            }
    	    if(InitilisationRessource.checkNextInitDay() && InitilisationRessource.checkToInitData()){
                NewsApiFetch sjs = new NewsApiFetch();
                Articlesfetched articlesFetched = sjs.fetchArticles();
                /**
                 * set new Article() from api data throught ArticleDTO mapping and do it once a day
                 * return Article dto from article (store in db)
                 * clean database existing articles
                 * =>TODO - uspiside action only if it was done -> find a paramètre
                 */
                for(int i=0; i<articlesFetched.getTotalResults(); i++){
                    List<Article> articleIsSets = entityDtoMapArticleOfTheDay.convertToEntity(
                            sjs.fetchArticles().getArticles(),
                            Article.class);
                    //articlesRepository.save(articleIsSets.get(0));
                    articlesRepository.saveAll(articleIsSets);
                }
                Initialisation initParam =  new Initialisation();
                initParam.setNextInitDate(DateUtils.tomorrow());
                InitilisationRessource.updateInitParam(initParam);
                //InitilisationRessource.updateInitParam();
            }
            /**
             * return directly articles if it's set in db
             */
            EntityDtoMap<ArticleDto,Article> entityDtoMapArticle = new EntityDtoMap();
            return ResponseEntity.status(HttpStatus.OK).body(entityDtoMapArticleOfTheDay
                    .convertToDto(articlesRepository.findAll(), ArticleDto.class));
    	}catch (Exception e){
    	    /*if(e instanceof  IOException ){
    	        return return ResponseEntity.c;
            }
            if(e instanceof JSONException){
                return "script fail from fetching url to json";
            }*/
            e.printStackTrace();
            List<ArticleDto> noArticles = new ArrayList<>();
            return ResponseEntity.status(HttpStatus.OK).body(noArticles);

        }
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

