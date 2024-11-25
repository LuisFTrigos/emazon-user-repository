package com.example.emazon_aux.infrastructure.configuration.security.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenException extends AuthenticationException {
    public TokenException(){
        super("A token problem has occurred");
    }
}
