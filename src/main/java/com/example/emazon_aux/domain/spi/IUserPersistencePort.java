package com.example.emazon_aux.domain.spi;

import com.example.emazon_aux.domain.model.RoleModel;
import com.example.emazon_aux.domain.model.UserModel;

import java.util.List;

public interface IUserPersistencePort {

    void saveUser(UserModel userModel);
    void registerUser(UserModel userModel);
    boolean existsByName(String name);
    boolean existsByDocument(String document);
    List<UserModel> getAllUsers(int page);
    boolean userAlreadyExists(String documentNumber);
    boolean mailAlreadyExists(String email);
    RoleModel getRole();
    //UserModel getUserById(Long id);
}
