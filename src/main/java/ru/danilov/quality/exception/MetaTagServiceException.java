package ru.danilov.quality.exception;

public class MetaTagServiceException extends RuntimeException{
    public MetaTagServiceException(String message) {
        super(message);
    }

    public MetaTagServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
