package com.backend.fluxnewsapi.dtos.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserDto {
    private int id;
    private String nom;
    private String prenom;
    private String username;
    private String password;
}
