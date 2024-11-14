package com.example.exception;

public class CarException extends RuntimeException {

    // Ajout de serialVersionUID pour assurer la compatibilité de la sérialisation
    private static final long serialVersionUID = 1L;

    public CarException(String message) {
        super(message);
    }

    public CarException(String message, Throwable cause) {
        super(message, cause);
    }
}
