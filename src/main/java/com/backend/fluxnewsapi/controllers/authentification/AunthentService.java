package com.backend.fluxnewsapi.controllers.authentification;

import com.backend.fluxnewsapi.dtos.models.UserAunt;
import com.backend.fluxnewsapi.models.User;
import com.backend.fluxnewsapi.services.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AunthentService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByUsername = usersRepository.findByUsername(username);
        UserAunt userAunt = new UserAunt();
        userAunt.setUsername(userByUsername.getUsername());
        userAunt.setPassword(userByUsername.getPassword());
        if(userAunt.getUsername() == null && userAunt.getPassword() == null) {
            return  new AuthentUser(userAunt);
            /*return new UsernamePasswordAuthenticationToken(
                    username, password, new ArrayList<>());*/
        } else {
            throw new BadCredentialsException("Authentication failed");
        }
    }
}
