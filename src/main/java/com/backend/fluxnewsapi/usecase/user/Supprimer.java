package com.backend.fluxnewsapi.usecase.user;

import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;

public class Supprimer {

    @Autowired
    private UserServices userService;

    public void suppression(Long id) throws RessourceException {
        userService.deleteUser(id);
    }
}
