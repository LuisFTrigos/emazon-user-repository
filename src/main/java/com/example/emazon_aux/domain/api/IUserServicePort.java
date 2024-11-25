package com.example.emazon_aux.domain.api;

import com.example.emazon_aux.domain.model.UserModel;

import java.util.List;

public interface IUserServicePort {

    void saveUser(UserModel userModel);

    void registerUser(UserModel userModel);

    List<UserModel> getAllUsers(int page);
    //UserModel getUserById(Long id);
}
