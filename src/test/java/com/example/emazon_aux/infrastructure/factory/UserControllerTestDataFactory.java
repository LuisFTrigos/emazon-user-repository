package com.example.emazon_aux.infrastructure.factory;

import com.example.emazon_aux.aplication.dto.request.AuthCredentials;
import com.example.emazon_aux.aplication.dto.request.UserRequestDto;
import com.example.emazon_aux.aplication.dto.response.UserResponseDto;
import com.example.emazon_aux.domain.model.RoleModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserControllerTestDataFactory {

    private static final String ACCESS_TOKEN_SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXvCJ9";

    private UserControllerTestDataFactory() {}

    public static UserRequestDto getUserRequest() {
        return new UserRequestDto(
                "User",
                "User",
                "1234567890",
                "1234567890",
                LocalDate.of(2000, 1, 1),
                "user@gmail.com",
                "password");

    }
    public static UserResponseDto getUserResponse() {
        return new UserResponseDto(
                "User",
                "User",
                "1234567890",
                "1234567890",
                LocalDate.of(2000, 1, 1),
                "user@gmail.com",
                new RoleModel(1L, "ADMIN", "ROLE_ADMIN"));

    }

    public static List<UserResponseDto> getUserResponseList() {
        UserResponseDto otherUser = new UserResponseDto();
        otherUser.setName("User2");
        otherUser.setLastName("User2");
        otherUser.setDocument(Integer.parseInt("12345678902"));
        otherUser.setPhone("+12345678902");
        otherUser.setBirthday(LocalDate.of(2000, 1, 1));
        otherUser.setEmail("user2@gmail.com");
        otherUser.setRole(new RoleModel(1L, "ADMIN", "ROLE_ADMIN"));

        List<UserResponseDto> userList = new ArrayList<>();
        userList.add(getUserResponse());
        userList.add(otherUser);

        return userList;

    }

    public static String createJwtToken(String role, String email) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", role);
        //Token expiration date
        Date expirationDate = new Date(System.currentTimeMillis() + 3_600_000);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static String asJsonString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        return objectMapper.writeValueAsString(object);
    }
}
