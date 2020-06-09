package com.backend.fluxnewsapi.controllers;

import com.backend.fluxnewsapi.dtos.models.UserAunt;
import com.backend.fluxnewsapi.exceptions.RessourceException;
import com.backend.fluxnewsapi.models.Initialisation;
import com.backend.fluxnewsapi.models.User;
import com.backend.fluxnewsapi.repository.UsersRepository;
import com.backend.fluxnewsapi.utils.DateUtils;
import com.backend.fluxnewsapi.utils.ErrorCode;
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
        //BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        //encrypt.encode(password);
        ResponseEntity rep;
        User user= usersRepository.findByUsername(userConn.getUsername());
         if(user.getConnectStatus() == true){
             return ResponseEntity.ok()
                     .body("login already succesful");
         }else {
             if ( userConn.getUsername().equals(user.getUsername())
                     && userConn.getPassword().equals(user.getPassword()) ) {
                 user.setConnectStatus(true);
                 usersRepository.save(user);
                rep = ResponseEntity.ok()
                         .header("username", userConn.getUsername())
                         .body("login succesful");
             } else {
                 return  ResponseEntity.ok()
                          .body("login fail");
             }
         }
         ckeckMisAJourFetchingArticle(user);
         return rep;
    }
    private void ckeckMisAJourFetchingArticle(User user){
        Initialisation init = user.getMisajour();
        if(!init.getUpdateday().equals(DateUtils.today()) || !init.getUpdateday().equals(DateUtils.tomorrow())){
            init.setToInitied(true);
            init.setUpdateday(DateUtils.today());
            user.setMisajour(init);
            usersRepository.save(user);
        }
    }

    @GetMapping("/logout/{iduser}")
    public ResponseEntity<String> logoutProcess(@PathVariable("iduser") Long  iduser) throws RessourceException {
        User user = usersRepository.findById(iduser)
                .orElseThrow( ()-> new RessourceException(ErrorCode.NOT_FOUND));

        if(user != null){
            user.setConnectStatus(false);
            usersRepository.save(user);
            return ResponseEntity.ok("logout Succesfull");
        }
        return ResponseEntity.ok("logout fail");
    }
}

