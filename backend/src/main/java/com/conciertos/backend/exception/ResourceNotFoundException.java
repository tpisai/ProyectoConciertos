package com.conciertos.backend.exception;

public class ResourceNotFoundException
        extends RuntimeException {

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}