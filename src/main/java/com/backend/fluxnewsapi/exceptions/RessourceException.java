package com.backend.fluxnewsapi.exceptions;

import com.backend.fluxnewsapi.utils.ErrorCode;

public class RessourceException extends Exception {
    public RessourceException(ErrorCode err) {
        super(String.format("Ressource %s",err.getMessage()));
    }
    public RessourceException(String err) {
        super(err)	;
    }
}
