package com.security.services.config.exceptions;

public class RequestCannotProcessException extends Exception {

    private static final long serialVersionUID = -6229665580233295463L;

    public RequestCannotProcessException(String msg) {
        super(msg);
    }
}
