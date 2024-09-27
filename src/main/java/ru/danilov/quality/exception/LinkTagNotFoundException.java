package ru.danilov.quality.exception;

public class LinkTagNotFoundException extends RuntimeException{
    public LinkTagNotFoundException(String message) {
        super(message);
    }
}
