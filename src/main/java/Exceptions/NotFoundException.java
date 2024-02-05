package Exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("ID " + id + " non trovato");
    }
    public NotFoundException(String message) {
        super(message);
    }
}