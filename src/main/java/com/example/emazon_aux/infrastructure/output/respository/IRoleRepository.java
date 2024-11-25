package com.example.emazon_aux.infrastructure.output.respository;

import com.example.emazon_aux.infrastructure.output.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);
}
