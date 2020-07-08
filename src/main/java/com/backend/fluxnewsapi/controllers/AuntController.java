package com.backend.fluxnewsapi.controllers;

import com.backend.fluxnewsapi.domain.dtos.models.UserAunt;
import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.domain.utils.AuthentCase;
import com.backend.fluxnewsapi.infrastucture.repository.UsersRepository;
import com.backend.fluxnewsapi.usecase.authent.Connection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/secure")
public class AuntController {
    private final Logger LOG = Logger.getLogger(this.getClass().getName());
    private UsersRepository usersRepository;
    public AuntController(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

/*    @GetMapping
    public ResponseEntity<UserAunt> authentificationStatus(){
        User userByUsername = usersRepository.
        return ResponseEntity.ok("hello secure!!");
    }*/

    @PostMapping("/login")
    public ResponseEntity<String> loginProcess(UserAunt userConn) throws RessourceException {
        Connection action = new Connection();
        AuthentCase aunt = action.seConnecte(userConn);
        return ResponseEntity.ok()
                        .header("username", userConn.getUsername())
                        .body(aunt.msg);

         //ckeckMisAJourFetchingArticle(user);
    }
/*    private void ckeckMisAJourFetchingArticle(User user){
        Initialisation init = user.getMisajour();
        if(!init.getUpdateday().equals(DateUtils.today()) || !init.getUpdateday().equals(DateUtils.tomorrow())){
            init.setToInitied(true);
            init.setUpdateday(DateUtils.today());
            user.setMisajour(init);
            usersRepository.save(user);
        }
    }*/

    @GetMapping("/logout/{iduser}")
    public ResponseEntity<String> logoutProcess(@PathVariable("iduser") Long  iduser) throws RessourceException {
        Connection action = new Connection();
        if(action.seDeconnecter(iduser)){
            return ResponseEntity.ok("logout Succesfull");
        }
        return ResponseEntity.ok("logout fail");
    }
}

