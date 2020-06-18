package com.backend.fluxnewsapi.domain.exceptions;

import com.backend.fluxnewsapi.domain.utils.ErrorCode;

public class RessourceException extends Exception {
    public RessourceException(ErrorCode err) {
        super(String.format("Ressource %s",err.getMessage()));
    }
    public RessourceException(String err) {
        super(err)	;
    }
}
