package com.server.project.comixconnexion.exceptions;


import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class CxHttpResponse {

    private boolean success;
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public CxHttpResponse(){

    }

    public CxHttpResponse(HttpStatus status, String message, List<String> errors, boolean success){
        super();
        this.success = success;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public CxHttpResponse(HttpStatus status, String message, String error, boolean success){
        super();
        this.success = success;
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
