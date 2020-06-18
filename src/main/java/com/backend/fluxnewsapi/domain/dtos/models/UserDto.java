package com.backend.fluxnewsapi.domain.dtos.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserDto {
    private long id;
    private String email;
    private String username;
    private String password;
    private Boolean connectStatus;
}
