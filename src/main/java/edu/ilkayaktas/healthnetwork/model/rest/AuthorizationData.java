package edu.ilkayaktas.healthnetwork.model.rest;

import org.springframework.http.HttpStatus;

/**
 * Created by aselsan on 27.03.2018 at 17:20.
 */

public class AuthorizationData {
    public HttpStatus httpStatus;
    public int code;
    public String message;

    public AuthorizationData(HttpStatus httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
