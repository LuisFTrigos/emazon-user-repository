package com.example.emazon_aux.infrastructure.output.mapper;

import com.example.emazon_aux.domain.model.RoleModel;
import com.example.emazon_aux.infrastructure.output.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleEntityMapper {

    RoleModel toRoleModel(RoleEntity roleEntity);
}
