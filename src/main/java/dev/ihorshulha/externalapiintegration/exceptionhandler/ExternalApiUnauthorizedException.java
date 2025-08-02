package dev.ihorshulha.externalapiintegration.exceptionhandler;

public class ExternalApiUnauthorizedException extends RuntimeException {
    public ExternalApiUnauthorizedException(String message) {
        super(message);
    }
}
