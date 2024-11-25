package com.example.emazon_aux.aplication.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRegister {

    private String name;
    private String lastName;
    private String document;
    private String email;
    private String phone;
    private String password;
}
