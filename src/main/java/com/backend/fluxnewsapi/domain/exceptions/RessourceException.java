package com.backend.fluxnewsapi.domain.exceptions;

public class RessourceException extends Exception {
    public RessourceException(ErrorCode err) {
        super(String.format("Ressource %s",err.getMessage()));
    }
    public RessourceException(String err) {
        super(err)	;
    }
}
