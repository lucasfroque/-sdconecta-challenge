package com.sdconecta.saudedigital.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object id) {
        super("Resource not found " + id);
    }
}
