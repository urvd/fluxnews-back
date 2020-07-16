package com.backend.fluxnewsapi.domain.utils;

public enum AuthentCase {
    LOGIN_SUCCESS("login success"),
    LOGIN_DONE("login already"),
    LOGIN_FAIL("login fail");

    public String msg;
    AuthentCase(String msg){
        this.msg = msg;
    }
}
