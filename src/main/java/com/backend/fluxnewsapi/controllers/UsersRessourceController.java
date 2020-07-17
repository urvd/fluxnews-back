package com.backend.fluxnewsapi.controllers;

import com.backend.fluxnewsapi.domain.dtos.models.UserDto;
import com.backend.fluxnewsapi.domain.exceptions.MyMappingException;
import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.infrastucture.repository.UsersRepository;
import com.backend.fluxnewsapi.usecase.user.Ajouter;
import com.backend.fluxnewsapi.usecase.user.Modifier;
import com.backend.fluxnewsapi.usecase.user.Rechercher;
import com.backend.fluxnewsapi.usecase.user.Supprimer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersRessourceController {

    private UsersRepository usersRepository;
    public UsersRessourceController(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }
    /**
     * Object: UserDto => nom, prenom, username, password
     * Object or String: UserSimpleDto => username
     * @return
     */
    @GetMapping("user/{id}")
    @ResponseBody
    public UserDto getUser(@RequestParam(required = false, value = "userid") Long userid,
                           @RequestParam(required = false, value = "username") String username)
            throws MyMappingException, RessourceException {
        Rechercher search = new Rechercher();
        if(userid != null){
            return search.getUserSearched(userid);
        }
        if(username != null){
            return search.getUserSearched(username);
        }
        return null;
    }

    @PostMapping("/user")
    public ResponseEntity<String> createNewUser(@RequestBody UserDto userDto) throws MyMappingException, RessourceException {
        Ajouter ajouter = new Ajouter();
        ajouter.newWelcomeUser(userDto);
        return ResponseEntity.ok("created");
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> modifyUser(@PathVariable(value = "id") Long id,
                                             @RequestBody  UserDto modifyUser) throws MyMappingException, IllegalAccessException, RessourceException {
        Modifier modifier = new Modifier();

        if(modifier.changeUser(id,modifyUser)){
            return ResponseEntity.ok("modified");
        }
        return ResponseEntity.ok("not modified");
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> removeUser(@PathVariable(value = "id") Long id) throws RessourceException {
        Supprimer suppression = new Supprimer();
        suppression.suppression(id);
        return ResponseEntity.ok("delete");
    }
}
