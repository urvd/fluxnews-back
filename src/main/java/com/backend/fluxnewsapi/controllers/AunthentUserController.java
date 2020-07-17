package com.backend.fluxnewsapi.controllers;

import com.backend.fluxnewsapi.domain.dtos.models.UserAunt;
import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.domain.utils.AuthentCase;
import com.backend.fluxnewsapi.usecase.authent.Connection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure")
public class AunthentUserController {

    @PostMapping("/login")
    public ResponseEntity<String> loginProcess(UserAunt userConn) throws RessourceException {
        Connection action = new Connection();
        AuthentCase aunt = action.seConnecte(userConn);
        return ResponseEntity.ok()
                        .header("username", userConn.getUsername())
                        .body(aunt.msg);
    }

    @GetMapping("/logout/{iduser}")
    public ResponseEntity<String> logoutProcess(@PathVariable("iduser") Long  iduser) throws RessourceException {
        Connection action = new Connection();
        if(action.seDeconnecter(iduser)){
            return ResponseEntity.ok("logout Succesfull");
        }
        return ResponseEntity.ok("logout fail");
    }
}

