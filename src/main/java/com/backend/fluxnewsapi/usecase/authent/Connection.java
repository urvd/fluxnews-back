package com.backend.fluxnewsapi.usecase.authent;

import com.backend.fluxnewsapi.domain.dtos.models.UserAunt;
import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.domain.utils.AuthentCase;
import com.backend.fluxnewsapi.infrastucture.models.User;
import com.backend.fluxnewsapi.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;

public class Connection {
    private User user;
    @Autowired
    private UserServices userServices;

/*    public enum  AuthtCase {
        LOGIN_SUCCESS("login success"),
        LOGIN_DONE("login already"),
        LOGIN_FAIL("login fail");

        public String msg;
        AuthtCase(String msg){
            this.msg = msg;
        }
    }*/

    public AuthentCase seConnecte(UserAunt userAunt) throws RessourceException {

        user = userServices.findUserWithUsername(userAunt.getUsername());
        if(user.getConnectStatus() == true){
            return  AuthentCase.LOGIN_DONE;
        }else {
            if ( userAunt.getUsername().equals(user.getUsername())
                    && userAunt.getPassword().equals(user.getPassword()) ) {
                user.setConnectStatus(true);
                return userServices.addUser(user)? AuthentCase.LOGIN_SUCCESS: AuthentCase.LOGIN_FAIL;
            } else {
                return  AuthentCase.LOGIN_FAIL;
            }
        }
    }

    public boolean seDeconnecter(Long iduser) throws RessourceException {
        User user = userServices.findUser(iduser);
        if(user != null){
            user.setConnectStatus(false);
            userServices.addUser(user);
            return true;
        }
        return false;
    }
}
