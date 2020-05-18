package com.backend.fluxnewsapi.controllers.authentification;

import com.backend.fluxnewsapi.models.User;
import com.backend.fluxnewsapi.services.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
public class AuntController {
    private final Logger LOG = Logger.getLogger(this.getClass().getName());
    private UsersRepository usersRepository;
    private UserAuthenticationProvider authenticationProvider;
    public AuntController(UsersRepository usersRepository,UserAuthenticationProvider authenticationProvider){
        this.authenticationProvider = authenticationProvider;
        this.usersRepository = usersRepository;
    }
    @GetMapping("/headers")
    public ResponseEntity<String> listAllHeaders(
            @RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            LOG.info(String.format("Header '%s' = %s", key, value));
        });

        return ResponseEntity.ok(
                String.format("Listed %d headers", headers.size()));
    }
    @GetMapping("/")
    public ResponseEntity<String> authentificationStatus(){
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return ResponseEntity.ok("autorize !!");
        }
        return ResponseEntity.ok("not autorize !!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginProcess(@RequestParam("username") String username,
                                               @RequestParam("password") String password ){
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            ResponseEntity.ok().body("login already succesful");
        }
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                username, password, new ArrayList<>());
        //authenticationProvider.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        if(authenticationToken.isAuthenticated()) {
            return ResponseEntity.ok()
                    .header("username", username)
                    .body("login succesful");
        }
        else return ResponseEntity.ok("login fail");
    }

    @GetMapping("/logout/{iduser}")
    public ResponseEntity<String> logoutProcess(@PathVariable("iduser") int  iduser,
                                               @RequestHeader("username") String username,
                                                HttpServletRequest request,
                                                HttpServletResponse response){
        Optional<User> user = usersRepository.findById(iduser);
        if(username==null){
            return null;
        }
        if(user.get() != null && username.equals(user.get().getUsername())){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            SecurityContextHolder.getContext().setAuthentication(auth);
            if(auth != null && auth.isAuthenticated()){
                auth.setAuthenticated(false);
                new SecurityContextLogoutHandler().logout(request, response, auth);
                return ResponseEntity.ok("logout Succesfull");
            }
        }
        return ResponseEntity.ok("logout fail");
    }
}

