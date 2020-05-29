package com.backend.fluxnewsapi.controllers.authentification;

import com.backend.fluxnewsapi.dtos.models.UserAunt;
import com.backend.fluxnewsapi.models.User;
import com.backend.fluxnewsapi.services.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/secure")
public class AuntController {
    private final Logger LOG = Logger.getLogger(this.getClass().getName());
    private UsersRepository usersRepository;
    public AuntController(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    //PasswordEncoder getPasswordEncoder(){
    //    return new BCryptPasswordEncoder();
    //}
    @GetMapping
    public ResponseEntity<String> authentificationStatus(){
        //User userByUsername = usersRepository.
        return ResponseEntity.ok("hello !!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginProcess(UserAunt userConn){
        //BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        //encrypt.encode(password);

        Optional<User> user= Optional.ofNullable(usersRepository.findByUsername(userConn.getUsername()));
         if(user.get().getConnectStatus() == true){
             return ResponseEntity.ok()
                     .body("login already succesful");
         }
         if (userConn.getUsername().equals(user.get().getUsername())
                && userConn.getPassword().equals(user.get().getPassword())) {
             user.get().setConnectStatus(true);
            usersRepository.save(user.get());
            return ResponseEntity.ok()
                    .header("username", userConn.getUsername())
                    .body("login succesful");
        } else {
            return ResponseEntity.ok("login fail");
        }
    }

    @GetMapping("/logout/{iduser}")
    public ResponseEntity<String> logoutProcess(@PathVariable("iduser") Long  iduser){
        Optional<User> user = usersRepository.findById(iduser);

        if(user.get() != null){
            user.get().setConnectStatus(false);
            usersRepository.save(user.get());
            return ResponseEntity.ok("logout Succesfull");
        }
        return ResponseEntity.ok("logout fail");
    }
}

