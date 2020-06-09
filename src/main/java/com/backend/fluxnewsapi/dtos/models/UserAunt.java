package com.backend.fluxnewsapi.dtos.models;

import lombok.Data;

@Data
public class UserAunt {
    public  UserAunt(){}
    public UserAunt(String status, String username) {
        this.username = username;
        this.status = status;
    }

    private String username;
    private String password;
    private String status;
}
