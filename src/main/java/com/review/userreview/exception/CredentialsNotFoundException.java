package com.review.userreview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Credential Not Found Exception
 */
@ResponseStatus(value= HttpStatus.FORBIDDEN)
public class CredentialsNotFoundException extends RuntimeException {
    /**
     * Unique ID for Serialized object
     */
    private static final long serialVersionUID = -1790211652911971721L;

    public CredentialsNotFoundException(String request) {
        super("Credentials dont match Exception :" + request);
    }
}