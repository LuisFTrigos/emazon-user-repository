package com.example.emazon_aux.aplication.handler;

import com.example.emazon_aux.aplication.dto.request.LoginRegister;
import com.example.emazon_aux.aplication.dto.request.UserRequestDto;
import com.example.emazon_aux.aplication.dto.response.GenericResponse;
import com.example.emazon_aux.aplication.dto.response.UserResponseDto;

import java.util.List;

public interface IUserHandler {

    GenericResponse saveUser(UserRequestDto userRequestDto);

    void registerUser(LoginRegister loginRegister);

    List<UserResponseDto> getAllUsers(int page);
   // UserResponseDto getUserById(Long id);

}
