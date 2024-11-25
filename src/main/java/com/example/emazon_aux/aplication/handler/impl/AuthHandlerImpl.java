package com.example.emazon_aux.aplication.handler.impl;

import com.example.emazon_aux.aplication.dto.response.JwtResponseDto;
import com.example.emazon_aux.aplication.handler.AuthHandler;
import com.example.emazon_aux.aplication.dto.request.AuthCredentials;
import com.example.emazon_aux.infrastructure.configuration.security.util.TokenUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthHandlerImpl implements AuthHandler {

    private final AuthenticationManager authenticationManager;


    @Override
    public JwtResponseDto login(AuthCredentials authCredentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authCredentials.getEmail(), authCredentials.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = TokenUtils.createToken(userDetails);
        return new JwtResponseDto(jwt);
    }

}
