package com.example.patitas.Exeptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{
    @Autowired
    private HttpStatus status;
    public ApiException(String mensaje, HttpStatus status){
        super(mensaje);
        this.status=status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
