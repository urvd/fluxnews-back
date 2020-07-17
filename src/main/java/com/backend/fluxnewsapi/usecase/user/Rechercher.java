package com.backend.fluxnewsapi.usecase.user;

import com.backend.fluxnewsapi.domain.dtos.EntityDtoMap;
import com.backend.fluxnewsapi.domain.dtos.models.UserDto;
import com.backend.fluxnewsapi.domain.exceptions.MyMappingException;
import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.infrastucture.models.User;
import com.backend.fluxnewsapi.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;

public class Rechercher {

    @Autowired
    private UserServices userService;

    public UserDto getUserSearched(String username) throws RessourceException, MyMappingException {
        EntityDtoMap<User, UserDto> entityDtoMap = new EntityDtoMap<>();
        UserDto userdto = entityDtoMap.convertToDto(userService.findUserWithUsername(username),UserDto.class);
        return userdto;
    }

    public UserDto getUserSearched(Long idUser) throws RessourceException, MyMappingException {
        EntityDtoMap<User, UserDto> entityDtoMap = new EntityDtoMap<>();
        UserDto userdto = entityDtoMap.convertToDto(userService.findUser(idUser),UserDto.class);
        return userdto;
    }
}
