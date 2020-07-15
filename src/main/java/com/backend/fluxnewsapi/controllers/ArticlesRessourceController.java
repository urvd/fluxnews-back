package com.backend.fluxnewsapi.controllers;

import com.backend.fluxnewsapi.domain.dtos.models.ArticleDto;
import com.backend.fluxnewsapi.domain.exceptions.MyMappingException;
import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.usecase.articles.MettreAJours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticlesRessourceController {

    @Autowired
    private MettreAJours articlesDuJours;
    @GetMapping("/all")
    public ResponseEntity<List<ArticleDto>> article() throws IOException, MyMappingException, RessourceException {
        return ResponseEntity.ok().body(articlesDuJours.fetch());
    }

    //ou

    @GetMapping
    public ResponseEntity<List<ArticleDto>> getArticles(@RequestParam(required = true, value = "userid") Long userid) throws MyMappingException, IOException, RessourceException {
        return ResponseEntity.ok().body(articlesDuJours.fetch(userid));
    }

}