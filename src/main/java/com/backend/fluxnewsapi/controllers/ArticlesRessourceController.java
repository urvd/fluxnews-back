package com.backend.fluxnewsapi.controllers;

import com.backend.fluxnewsapi.domain.dtos.models.ArticleDto;
import com.backend.fluxnewsapi.domain.exceptions.MyMappingException;
import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.usecase.articles.MettreAJours;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticlesRessourceController {

    @GetMapping("/all")
    public ResponseEntity<List<ArticleDto>> article() throws IOException, MyMappingException, RessourceException {
        final MettreAJours articlesDuJours = new MettreAJours();
        return ResponseEntity.ok().body(articlesDuJours.fetch());
    }

/*    @GetMapping("/article/{userid}")
    public ResponseEntity<List<ArticleDto>> getArticles(@PathVariable(value = "userid") Long userid) throws JSONException, MyMappingException, IOException, ClassNotFoundException, RessourceException {
        final MettreAJours articlesDuJours = new MettreAJours();
        return ResponseEntity.ok().body(articlesDuJours.fetch(userid));
    }*/
}