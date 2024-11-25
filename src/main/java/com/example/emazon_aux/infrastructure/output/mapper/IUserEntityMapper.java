package com.example.emazon_aux.infrastructure.output.mapper;

import com.example.emazon_aux.domain.model.UserModel;
import com.example.emazon_aux.infrastructure.output.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {

    @Mapping(source = "idRole", target = "idRole.id")
    @Mapping(source = "document", target = "idDocument")
    UserEntity toUserEntity(UserModel userModel);

    //@Mapping(target = "roleModel", source = "roleEntity")
    //UserModel toUserModel(UserEntity userEntity);


    // List<UserModel> toUserModelList(List<UserEntity> userEntityList);



}
