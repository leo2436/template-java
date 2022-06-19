package org.oobootcamp.core.exception;

public class InvalidTicketException extends RuntimeException {
    public InvalidTicketException() {
        super("Invalid ticket");
    }
}
