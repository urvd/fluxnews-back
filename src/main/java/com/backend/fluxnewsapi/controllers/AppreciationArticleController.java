package com.backend.fluxnewsapi.controllers;

import com.backend.fluxnewsapi.domain.dtos.models.Appreciation;
import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.usecase.userArticles.Apprecier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article/appreciation")
public class AppreciationArticleController {

    @GetMapping("/get")
    public ResponseEntity<Appreciation> currentAppreciation(@RequestParam(required = true, value = "user") Long iduser,
                                                            @RequestParam(required = false, value = "article") Long idArticle)
            throws RessourceException {

        Apprecier apprecier = new Apprecier();
        return  ResponseEntity.ok(apprecier.getCurrent(iduser,idArticle));
    }

    @PostMapping("/add")
    public ResponseEntity addAppreciation(@RequestBody Appreciation appreciation) throws RessourceException {
        Apprecier apprecier = new Apprecier();
        apprecier.add(appreciation);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/change")
    public ResponseEntity changeAppreciation(@RequestBody Appreciation appreciation) throws RessourceException {
        Apprecier apprecier = new Apprecier();
        apprecier.modify(appreciation);
        return ResponseEntity.ok().build();
    }
}
