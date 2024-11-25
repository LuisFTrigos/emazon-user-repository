package com.example.emazon_aux.aplication.handler;

import com.example.emazon_aux.aplication.dto.response.JwtResponseDto;
import com.example.emazon_aux.aplication.dto.request.AuthCredentials;

public interface AuthHandler {
    JwtResponseDto login(AuthCredentials authCredentials);
}
