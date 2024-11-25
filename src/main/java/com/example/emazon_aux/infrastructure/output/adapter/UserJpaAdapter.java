package com.example.emazon_aux.infrastructure.output.adapter;

import com.example.emazon_aux.domain.model.RoleModel;
import com.example.emazon_aux.domain.model.UserModel;
import com.example.emazon_aux.domain.spi.IUserPersistencePort;
import com.example.emazon_aux.domain.util.constants.Constants;
import com.example.emazon_aux.infrastructure.output.entity.UserEntity;
import com.example.emazon_aux.infrastructure.output.mapper.IRoleEntityMapper;
import com.example.emazon_aux.infrastructure.output.mapper.IUserEntityMapper;
import com.example.emazon_aux.infrastructure.output.respository.IRoleRepository;
import com.example.emazon_aux.infrastructure.output.respository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.emazon_aux.infrastructure.output.adapter.actions.RoleAuthentication.getRoleWithAuthentication;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public void saveUser(UserModel userModel) {
        UserEntity userEntity = userEntityMapper.toUserEntity(userModel);
        userRepository.save(userEntity);
    }

    @Override
    public void registerUser(UserModel userModel) {
        userRepository.save(userEntityMapper.toUserEntity(userModel));
    }

    @Override
    public boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

    @Override
    public boolean existsByDocument(String document) {
        return userRepository.existsByIdDocument(document);
    }

    @Override
    public List<UserModel> getAllUsers(int page) {
        Pageable pagination = PageRequest.of(page, Constants.MAX_PAGE_SIZE);
        return List.of();
    }

    @Override
    public boolean userAlreadyExists(String documentNumber) {
        return userRepository.existsByIdDocument(documentNumber);
    }

    @Override
    public boolean mailAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public RoleModel getRole() {
        return roleEntityMapper.toRoleModel(getRoleWithAuthentication(roleRepository));
    }

    /*@Override
    public UserModel getUserById(Long id) {
        return userEntityMapper.toUserModel(userRepository.findById(id).orElse(null));
    }*/
}
