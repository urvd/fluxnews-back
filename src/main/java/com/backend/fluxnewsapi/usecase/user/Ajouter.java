package com.backend.fluxnewsapi.usecase.user;

import com.backend.fluxnewsapi.domain.dtos.EntityDtoMap;
import com.backend.fluxnewsapi.domain.dtos.models.UserDto;
import com.backend.fluxnewsapi.domain.exceptions.ErrorCode;
import com.backend.fluxnewsapi.domain.exceptions.MyMappingException;
import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.infrastucture.models.ArticleUser;
import com.backend.fluxnewsapi.infrastucture.models.User;
import com.backend.fluxnewsapi.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Ajouter {

    @Autowired
    private UserServices userService;

    public boolean newWelcomeUser(UserDto userDto) throws MyMappingException, RessourceException {
        /**
         * Verifier que l'utilisateur exist
         */
        User userByEmail = userService.findUserWithEmail(userDto.getEmail());
        User userByUserame = userService.findUserWithUsername(userDto.getUsername());
        if(userByUserame != null || userByEmail != null && userByEmail.equals(userByUserame)){
            throw new RessourceException(ErrorCode.EXIST);
        }

        /**
         * Save the new user and save a refence of it in herited reference of ArticleUser which it's required
         */
        EntityDtoMap<User,UserDto> entityDtoMap = new EntityDtoMap<>();
        User user = entityDtoMap.convertToEntity(userDto,User.class);
        user.setConnectStatus(true);

        List<ArticleUser> registerUser20Times = new ArrayList<>();
        for( int i = 0; i < 20; i++){
            ArticleUser ua = new ArticleUser(user);
            registerUser20Times.add(ua);
        }
        user.setArticleUsers(registerUser20Times);

        return userService.saveUser(user);
    }
}
