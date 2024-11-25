package com.example.emazon_aux.aplication.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @NotNull(message = "The field name cannot null")
    @NotEmpty(message = "The field name cannot empty")
    private String name;

    @NotNull(message = "The field last name cannot null")
    @NotEmpty(message = "The field last name cannot empty")
    private String lastName;

    @NotNull(message = "The field document cannot null")
    @NotEmpty(message = "The field document cannot empty")
    @Max(value = 10, message = "The field document cannot be max 10 units")
    private String document;

    @NotNull(message = "The field phone cannot null")
    @NotEmpty(message = "The field phone cannot empty")
    @Max(value = 13, message = "The field phone cannot be max 10 units")
    private String phone;

    @Email(message = "The email format is invalid")
    @NotNull(message = "The field email cannot null")
    @NotEmpty(message = "The field email cannot empty")
    private String email;

    @NotNull(message = "The field password cannot null")
    @NotEmpty(message = "The field password cannot empty")
    private String password;

    @NotNull(message = "The field birthday cannot null")
    @NotEmpty(message = "The field birthday cannot empty")
    private LocalDate birthday;



    private Long idRole;

    public UserRequestDto(String user, String user1, String number, String number1, LocalDate of, String mail, String password) {
    }
}
