package com.example.emazon_aux.infrastructure.input;

import com.example.emazon_aux.aplication.dto.request.UserRequestDto;
import com.example.emazon_aux.aplication.dto.response.JwtResponseDto;
import com.example.emazon_aux.aplication.dto.response.UserResponseDto;
import com.example.emazon_aux.aplication.handler.AuthHandler;
import com.example.emazon_aux.aplication.dto.request.AuthCredentials;
import com.example.emazon_aux.domain.util.constants.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthHandler authHandler;

    @Operation(summary = "Login to obtain the token",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Session token",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = JwtResponseDto.class))),
                    @ApiResponse(responseCode = "401", description = "Wrong credentials",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody AuthCredentials authCredentials) {
        return new ResponseEntity<>(authHandler.login(authCredentials), HttpStatus.OK);
    }


    /*@Operation(summary = "Register a customer user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User message created",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "403", description = "User cannot be a minor",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "409", description = "User already exists",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody UserRequestDto userRegister) {
        authHandler.register(userRegister);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        Collections.singletonMap(
                                Constants.RESPONSE_MESSAGE_KEY,
                                Constants.USER_CREATED_MESSAGE
                        )
                );
    }*/

}
