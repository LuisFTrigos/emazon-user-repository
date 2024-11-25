package com.example.emazon_aux.domain.factory;

import com.example.emazon_aux.domain.model.RoleModel;
import com.example.emazon_aux.domain.model.UserModel;

import java.time.LocalDate;

public class UseCaseDataFactory {

    public static RoleModel getRoleAdminWithSetters() {
        RoleModel roleModel = new RoleModel();

        roleModel.setId(1L);
        roleModel.setName("ADMIN");
        roleModel.setDescription("PLACE MANAGER");
        return  roleModel;
    }

    public static RoleModel getRoleAuxWithSetters() {
        RoleModel roleModel = new RoleModel();

        roleModel.setId(2L);
        roleModel.setName("AUX_BODEGA");
        roleModel.setDescription("AUX_ROLE");
        return  roleModel;
    }
    public static RoleModel getRoleCustomerWithSetters() {
        RoleModel roleModel = new RoleModel();

        roleModel.setId(4L);
        roleModel.setName("CUSTOMER");
        roleModel.setDescription("CUSTOMER_ROLE");
        return  roleModel;
    }

    public static UserModel getUserAdminWithSetters() {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setName("Admin");
        userModel.setLastName("Admin");
        userModel.setDocument("1234567890");
        userModel.setPhone("+571234567890");
        userModel.setEmail("admin@gmail.com");
        userModel.setPassword("admin123");
        userModel.setBirthday(LocalDate.of(2000, 1, 1));

        return userModel;
    }

    public static UserModel getUserAuxWithSetters() {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setName("Aux_bodega");
        userModel.setLastName("Aux_bodega");
        userModel.setDocument("1234567890");
        userModel.setPhone("+571234567890");
        userModel.setEmail("aux@gmail.com");
        userModel.setPassword("owner123");
        userModel.setBirthday(LocalDate.of(2000, 1, 1));

        return userModel;
    }

    public static UserModel getUserCustomerWithSetters() {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setName("Customer");
        userModel.setLastName("Customer");
        userModel.setDocument("1234567890");
        userModel.setPhone("+571234567890");
        userModel.setEmail("customer@gmail.com");
        userModel.setPassword("customer123");
        userModel.setBirthday(LocalDate.of(2000, 1, 1));

        return userModel;
    }

    public static RoleModel getRoleModel() {
        return new RoleModel(1L, "ADMIN", "PLACE MANAGER");
    }

    public static UserModel getUserModelEmpty() {
        return new UserModel(1L,
                "",
                "",
                "",
                "",
                LocalDate.of(2000, 1, 1),
                "test@.com",
                "",
                getRoleModel());
    }

    public static UserModel getOtherUserModelWithSetters() {
        UserModel userModel = new UserModel();
        userModel.setId(2L);
        userModel.setName("Name");
        userModel.setLastName("LastName");
        userModel.setDocument("1234567890");
        userModel.setPhone("+571234567890");
        userModel.setEmail("test2@gmail.com");
        userModel.setPassword("test123");
        userModel.setBirthday(LocalDate.of(2000, 1, 1));

        return userModel;
    }
}
