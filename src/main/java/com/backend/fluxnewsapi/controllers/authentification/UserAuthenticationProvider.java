package com.backend.fluxnewsapi.controllers.authentification;

import com.backend.fluxnewsapi.dtos.models.UserAunt;
import com.backend.fluxnewsapi.models.User;
import com.backend.fluxnewsapi.services.UsersRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private UsersRepository usersRepository;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        if(!authentication.isAuthenticated())return  authentication;
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User userByUsername = usersRepository.findByUsername(username);
        User userbyEmail = usersRepository.findByEmail(username);
        UserAunt userAunt = new UserAunt();
        userAunt.setUsername(userByUsername!=null?userByUsername.getUsername():userbyEmail.getEmail());
        userAunt.setPassword(userByUsername!=null?userByUsername.getPassword():userbyEmail.getPassword());
        if (username.equals(userAunt.getUsername()) && password.equals(userAunt.getPassword()) ) {
            return authentication;
        } else {

            throw new BadCredentialsException("Authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
