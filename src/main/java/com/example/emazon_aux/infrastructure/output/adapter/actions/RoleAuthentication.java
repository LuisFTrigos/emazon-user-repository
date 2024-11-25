package com.example.emazon_aux.infrastructure.output.adapter.actions;

import com.example.emazon_aux.domain.util.constants.Constants;
import com.example.emazon_aux.infrastructure.output.entity.RoleEntity;
import com.example.emazon_aux.infrastructure.output.respository.IRoleRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class RoleAuthentication {

    private RoleAuthentication() {}

    public static RoleEntity getRoleWithAuthentication(IRoleRepository roleRepository) {
        //Gets the session context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //The role is extracted from the session
        String roleName = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);
        //Gets the id of the role by its name in session
        assert roleName != null;
        Long idRole = provideRoleByName(roleName);

        return roleRepository.findById(idRole).orElse(null);
    }

    public static Long provideRoleByName(String roleName) {
        Long idRole = 0L;
        if (roleName.equalsIgnoreCase(Constants.ADMIN_ROLE)) {
            idRole = Constants.OWNER_ROLE_ID;
        } else if (roleName.equalsIgnoreCase(Constants.OWNER_ROLE)) {
            idRole = Constants.AUX_ROLE_ID;
        }
        return idRole;
    }
}
