package com.backend.fluxnewsapi.routes;

import com.backend.fluxnewsapi.config.Script;
import com.backend.fluxnewsapi.services.UsersRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.script.ScriptException;
import java.util.HashMap;

@RestController
@RequestMapping("/users")
public class UsersRessource {

    private UsersRepository usersRepository;

    public UsersRessource(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }
    @GetMapping("/js")
    String get() throws ScriptException {
        Script sjs = Script.getInstance();
        //sjs.runJavascript();
        return (String) sjs.helloJavascript();
    }

    /**
     * Object: UserDto => nom, prenom, username, password
     * Object or String: UserSimpleDto => username
     * @return
     */

    @GetMapping
    public HashMap<String,Object> getUsers(){
        return null;
    }

    @GetMapping("/user/{nom}")
    public HashMap<String,Object> getUser(@Param(value = "nom")  String name, String password){
        return null;
    }

    @PostMapping("/user")
    public HashMap<String,Object> newUser(@Param(value = "nom")  String nom,
                                          @Param(value = "prenom")  String prenom,
                                          @Param(value = "username") String username,
                                          @Param(value = "password") String password ) /* ou UserDto user*/{

        return null;
    }

    @PutMapping("/user/{id}")
    public HashMap<String,Object> modifyUser(@PathVariable(value = "id") Long id/*, UserDto user*/){
        return null;
    }

/*    @DeleteMapping("/user/{id}")
    public HashMap<String, Object> removeUser(@PathVariable(value = "id") Long id){
        return null;
    }*/
}
