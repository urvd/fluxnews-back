package com.backend.fluxnewsapi.controllers;

import com.backend.fluxnewsapi.domain.dtos.EntityDtoMap;
import com.backend.fluxnewsapi.domain.dtos.models.UserDto;
import com.backend.fluxnewsapi.domain.exceptions.MyMappingException;
import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.infrastucture.models.User;
import com.backend.fluxnewsapi.infrastucture.repository.UsersRepository;
import com.backend.fluxnewsapi.domain.utils.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/users")
public class UsersRessourceController {
    private final Logger LOG = Logger.getLogger(this.getClass().getName());
    private UsersRepository usersRepository;
    public UsersRessourceController(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }
    /**
     * Object: UserDto => nom, prenom, username, password
     * Object or String: UserSimpleDto => username
     * @return
     */
    @GetMapping
    @ResponseBody
    public List<UserDto> getUsers() throws MyMappingException, ClassNotFoundException {
        EntityDtoMap<User,UserDto> map = new EntityDtoMap<>();
        return map.convertToDto(usersRepository.findAll(),UserDto.class);
    }

/*    @GetMapping("/login{username,password}")
    public ResponseEntity<?> getUser(@PathVariable(value = "username",required = true) String username,
                                     @PathVariable(value = "password",required = true) String password) throws MyMappingException, ClassNotFoundException {
        for(UserDto user: getUsers()){
            if(user.getPassword().equals(password) && user.getUsername().equals(username)){
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.ok("aunt failure");
    }*/

    @PostMapping("/user")
    public ResponseEntity<String> createNewUser(@RequestBody UserDto userDto) throws MyMappingException, RessourceException {
        /**
         * Verifier que l'utilisateur exist
         */
        User userByEmail = usersRepository.findByEmail(userDto.getEmail());
        User userByUserame = usersRepository.findByUsername(userDto.getUsername());
        if(userByUserame != null || userByEmail != null){
            throw new RessourceException(ErrorCode.EXIST);
        }
        /**
         * creer un id et comparer avec ids existant
         */

        /**
         * Save the new user and save a refence of it in others tables which it's required
         */
        EntityDtoMap<User,UserDto> entityDtoMap = new EntityDtoMap<>();
        User user = entityDtoMap.convertToEntity(userDto,User.class);
        user.setConnectStatus(true);

        usersRepository.save(user);
        return ResponseEntity.ok("created");
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> modifyUser(@PathVariable(value = "id") Long id,
                                             @RequestBody  UserDto modifyUser) throws MyMappingException, IllegalAccessException, RessourceException {
        User userSearch = usersRepository.findById(id)
                .orElseThrow(() -> new RessourceException(ErrorCode.NOT_FOUND));
        EntityDtoMap<User,UserDto> entityDtoMap = new EntityDtoMap<>();
        UserDto userdto = entityDtoMap.convertToDto(userSearch,UserDto.class);
        Boolean hasModif = false;
        if(!userdto.getEmail().equals(modifyUser.getEmail())){
            userSearch.setEmail(modifyUser.getEmail());
            hasModif = true;
        }

        if(!userdto.getUsername().equals(modifyUser.getUsername())){
            userSearch.setUsername(modifyUser.getUsername());
            hasModif = true;
        }

        if(!userdto.getPassword().equals(modifyUser.getPassword())){
            userSearch.setPassword(modifyUser.getPassword());
            hasModif = true;
        }
        if(hasModif){
            usersRepository.save(userSearch);
            return ResponseEntity.ok("modified");
        }
        return ResponseEntity.ok("not modified");
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> removeUser(@PathVariable(value = "id") Long id) throws RessourceException {
        User user = usersRepository.findById(id)
                .orElseThrow(() -> new RessourceException(ErrorCode.NOT_FOUND));
        usersRepository.delete(user);
        return ResponseEntity.ok("delete");
    }
}
