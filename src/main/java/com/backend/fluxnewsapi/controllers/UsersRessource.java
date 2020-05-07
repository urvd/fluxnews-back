package com.backend.fluxnewsapi.controllers;

import com.backend.fluxnewsapi.dtos.ConvertMap;
import com.backend.fluxnewsapi.dtos.EntityDtoMap;
import com.backend.fluxnewsapi.dtos.models.UserDto;
import com.backend.fluxnewsapi.exceptions.MyMappingException;
import com.backend.fluxnewsapi.models.User;
import com.backend.fluxnewsapi.services.InitialisationRepository;
import com.backend.fluxnewsapi.services.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersRessource {

    private UsersRepository usersRepository;
    public UsersRessource(UsersRepository usersRepository, InitialisationRepository initRepository){
        this.usersRepository = usersRepository;
        //this.initRepository = initRepository;
    }
    /**
     * Object: UserDto => nom, prenom, username, password
     * Object or String: UserSimpleDto => username
     * @return
     */

    //@GetMapping
    public List<UserDto> getUsers() throws MyMappingException, ClassNotFoundException {
        EntityDtoMap<User,UserDto> map = new EntityDtoMap<>();
        return map.convertToDto(usersRepository.findAll(),UserDto.class);
    }

    @GetMapping("/login{username,password}")
    public ResponseEntity<?> getUser(@PathVariable(value = "username",required = true) String username,
                                     @PathVariable(value = "password",required = true) String password){
        try{
            for(UserDto user: getUsers()){
               if(user.getPassword().equals(password) && user.getUsername().equals(username)){
                   return ResponseEntity.ok(user);
               }
            }
            return ResponseEntity.ok("aunt failure");
        }catch(Exception e){
            return null;
        }
    }

    @PostMapping("/user")
    public ResponseEntity<String> createNewUser(/*@RequestParam(name = "nom")  String nom,
                                          @RequestParam(name = "prenom")  String prenom,
                                          @RequestParam(name = "username") String username,
                                          @RequestParam(name = "password") String password*/ HashMap<String,Object> newUserMap) throws IllegalAccessException {
        UserDto userDto = new UserDto();
        ConvertMap<UserDto> convert = new ConvertMap<>();
        userDto = convert.convertMapToObject(newUserMap);

        try{
            User user = new User();
            EntityDtoMap<User,UserDto> entityDtoMap = new EntityDtoMap<>();
            user = entityDtoMap.convertToEntity(userDto,User.class);
            usersRepository.save(user);
            /**
             * create Initialisation entity.
             */
            InitilisationRessource.createInitialisationParam();
            return ResponseEntity.ok("success");
        }catch(Exception e){
            return null;
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> modifyUser(@PathVariable(value = "id") Integer id,HashMap<String,Object> modifyUserMap) throws MyMappingException, IllegalAccessException {
        Optional<User> userSearch = usersRepository.findById(id);
        ConvertMap<UserDto> convert = new ConvertMap<>();

        UserDto userdtoCmpare = convert.convertMapToObject(modifyUserMap);
        EntityDtoMap<User,UserDto> entityDtoMap = new EntityDtoMap<>();
        UserDto userdto = entityDtoMap.convertToDto(userSearch.get(),UserDto.class);
        Boolean hasModif = false;
        if(!userdto.getUsername().equals(userdtoCmpare.getUsername())){
            userSearch.get().setUsername(userdtoCmpare.getUsername());
            hasModif = true;
        }

        if(!userdto.getPassword().equals(userdtoCmpare.getPassword())){
            userSearch.get().setPassword(userdtoCmpare.getPassword());
            hasModif = true;
        }
        if(hasModif){
            usersRepository.save(userSearch.get());
            return ResponseEntity.ok("modified");
        }
        return ResponseEntity.ok("not modified");
    }

/*    @DeleteMapping("/user/{id}")
    public HashMap<String, Object> removeUser(@PathVariable(value = "id") Long id){
        return null;
    }*/
}
