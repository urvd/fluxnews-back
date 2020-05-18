package com.backend.fluxnewsapi.controllers.authentification;

import com.backend.fluxnewsapi.dtos.models.UserAunt;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class AuthentUser implements UserDetails{

    private String username;
    private String password;
    private boolean enable;
    private boolean lock;
    private boolean expiredAccount;
    private  boolean expiredCredential;
    public AuthentUser(UserAunt user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enable = true;
        this.expiredAccount = false;
        this.expiredCredential = false;
        this.lock = false;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return expiredAccount;
    }

    @Override
    public boolean isAccountNonLocked() {
        return lock;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return expiredCredential;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
