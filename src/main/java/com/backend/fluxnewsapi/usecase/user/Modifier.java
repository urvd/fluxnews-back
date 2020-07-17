package com.backend.fluxnewsapi.usecase.user;

import com.backend.fluxnewsapi.domain.dtos.EntityDtoMap;
import com.backend.fluxnewsapi.domain.dtos.models.UserDto;
import com.backend.fluxnewsapi.domain.exceptions.MyMappingException;
import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.infrastucture.models.User;
import com.backend.fluxnewsapi.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;

public class Modifier {

    @Autowired
    private UserServices userService;

    public boolean changeUser(Long id, UserDto modifyUser) throws MyMappingException, RessourceException {
        User userSearch = userService.findUser(id);

        EntityDtoMap<User, UserDto> entityDtoMap = new EntityDtoMap<>();
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
            userService.saveUser(userSearch);
            return true;
        }
        return false;
    }
}
