package com.example.emazon_aux.domain.usecase;

import com.example.emazon_aux.domain.api.IUserServicePort;
import com.example.emazon_aux.domain.exception.*;
import com.example.emazon_aux.domain.model.RoleModel;
import com.example.emazon_aux.domain.model.UserModel;
import com.example.emazon_aux.domain.spi.IUserPersistencePort;
import com.example.emazon_aux.domain.util.constants.Constants;
import com.example.emazon_aux.domain.util.password.UserPasswordEncrypt;

import java.util.List;

import static com.example.emazon_aux.domain.util.validations.Validations.validationsUser;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final UserPasswordEncrypt userPasswordEncrypt;

    public UserUseCase(IUserPersistencePort userPersistencePort, UserPasswordEncrypt userPasswordEncrypt) {
        this.userPersistencePort = userPersistencePort;
        this.userPasswordEncrypt = userPasswordEncrypt;
    }

    @Override
    public void saveUser(UserModel userModel) {
        validationsUser(userModel);
        userModel.setPassword(userPasswordEncrypt.passwordEncoder(userModel.getPassword()));
        if (userPersistencePort.existsByName(userModel.getName())) {
            throw new UserAlreadyExistsException(Constants.USER_ALREADY_EXIST + userModel.getName());
        }
        if (userPersistencePort.existsByDocument(userModel.getDocument())){
            throw new UserAlreadyExistsException(Constants.DOCUMENT_ALREADY_EXIST + userModel.getDocument());
        }
        if (userPersistencePort.mailAlreadyExists(userModel.getEmail())) {
            throw new UserAlreadyExistsException(Constants.MAIL_ALREADY_EXIST + userModel.getEmail());
        }
        userPersistencePort.saveUser(userModel);
    }

    @Override
    public void registerUser(UserModel userModel) {
        validationsUser(userModel);
        RoleModel roleModel = new RoleModel();
        roleModel.setId(Constants.CUSTOMER_ROLE_ID);
        userModel.setPassword(userPasswordEncrypt.passwordEncoder(userModel.getPassword()));
        userPersistencePort.registerUser(userModel);
    }

    @Override
    public List<UserModel> getAllUsers(int page) {
        List<UserModel> list = userPersistencePort.getAllUsers(page);
        if (list.isEmpty()) {
            throw new NoDataFoundException();
        }
        return list;
    }

}
