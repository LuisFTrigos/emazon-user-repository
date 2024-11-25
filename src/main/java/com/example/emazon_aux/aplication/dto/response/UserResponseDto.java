package com.example.emazon_aux.aplication.dto.response;

import com.example.emazon_aux.domain.model.RoleModel;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String name;
    private String lastName;
    private int document;
    private String phone;
    private String email;
    private LocalDate birthday;
    private RoleModel role;

    public UserResponseDto(String user, String user1, String number, String number1, LocalDate of, String mail, RoleModel role) {
    }
}
