package com.example.emazon_aux.domain;

import com.example.emazon_aux.domain.exception.RoleNotFoundException;
import com.example.emazon_aux.domain.exception.UserAlreadyExistsException;
import com.example.emazon_aux.domain.factory.UseCaseDataFactory;
import com.example.emazon_aux.domain.model.UserModel;
import com.example.emazon_aux.domain.spi.IUserPersistencePort;
import com.example.emazon_aux.domain.usecase.UserUseCase;
import com.example.emazon_aux.domain.util.password.UserPasswordEncrypt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class UseCaseTest {

    @Mock
    UserPasswordEncrypt userPasswordEncrypt;

    @Mock
    IUserPersistencePort userPersistencePort;

    @InjectMocks
    UserUseCase userUseCase;

    @Test
    void shouldCreateUser() {
        //Given
        UserModel userModel = UseCaseDataFactory.getUserAuxWithSetters();


        //When
        Mockito.when(userPersistencePort.userAlreadyExists(userModel.getDocument()))
                .thenReturn(false);
        Mockito.doNothing().when(userPersistencePort).saveUser(userModel);
        userUseCase.saveUser(userModel);

        //Then
        Mockito.verify(userPersistencePort).saveUser(userModel);
    }



    @Test
    void shouldThrowUserAlreadyExistsException() {
        //Given
        UserModel userModel = UseCaseDataFactory.getUserAdminWithSetters();

        //When
        Mockito.when(userPersistencePort.userAlreadyExists(userModel.getDocument()))
                .thenReturn(true);

        //Then
        assertThrows(UserAlreadyExistsException.class, () -> userUseCase.saveUser(userModel));
    }
    @Test
    void shouldThrowRoleNotFoundException() {
        //Given
        UserModel userModel = UseCaseDataFactory.getUserAuxWithSetters();

        //When
        Mockito.when(userPersistencePort.userAlreadyExists(userModel.getDocument()))
                .thenReturn(false);
        Mockito.when(userPersistencePort.mailAlreadyExists(userModel.getEmail()))
                .thenReturn(false);
        Mockito.when(userPersistencePort.getRole()).thenReturn(null);

        //Then
        assertThrows(RoleNotFoundException.class, () -> userUseCase.saveUser(userModel));
    }

    /*@Test
    void shouldThrowRoleNotAllowedForCreationException() {
        //Given
        UserModel userModel = UseCaseDataFactory.getUserAdminWithSetters();
        RoleModel roleModel = UseCaseDataFactory.getRoleAdminWithSetters();

        //When
        Mockito.when(userPersistencePort.userAlreadyExists(userModel.getDocument()))
                .thenReturn(false);

        //Then
        assertThrows(RoleNotFoundException.class, () -> userUseCase.saveUser(userModel));
    }

    @Test
    void shouldGetAllUsers() {
        //Given
        RoleModel roleModel = UseCaseDataFactory.getRoleAdminWithSetters();
        UserModel userModel1 = UseCaseDataFactory.getUserAdminWithSetters();
        UserModel userModel2 = UseCaseDataFactory.getOtherUserModelWithSetters();

        List<UserModel> userModelList = new ArrayList<>();
        userModelList.add(userModel1);
        userModelList.add(userModel2);

        //When
        Mockito.when(userPersistencePort.getAllUsers(0))
                .thenReturn(userModelList);
        List<UserModel> result = userUseCase.getAllUsers(0);

        //Then
        assertEquals(userModelList, result);
        assertEquals(userModelList.get(0).getId(), result.get(0).getId());
        assertEquals(userModelList.get(0).getName(), result.get(0).getName());
        assertEquals(userModelList.get(0).getLastName(), result.get(0).getLastName());
        assertEquals(userModelList.get(0).getDocument(), result.get(0).getDocument());
        assertEquals(userModelList.get(0).getPhone(), result.get(0).getPhone());
        assertEquals(userModelList.get(0).getEmail(), result.get(0).getEmail());
        assertEquals(userModelList.get(0).getPassword(), result.get(0).getPassword());

        //Verify
        Mockito.verify(userPersistencePort).getAllUsers(0);
    }

    @Test
    void shouldThrowNoDataFoundException() {
        //When
        Mockito.when(userPersistencePort.getAllUsers(10))
                .thenReturn(Collections.emptyList());

        //Then
        assertThrows(NoDataFoundException.class, () -> userUseCase.getAllUsers(10));
    }



    @Test
    void shouldRegisterUser() {
        //Given
        UserModel userModel = UseCaseDataFactory.getUserCustomerWithSetters();
        String passwordEncrypted = "$2a$10$G.2DHaPhPXfVzh/bn71KruzG/13XPjfwP6pRVOjeBGGCAEL0CU51W";

        //When
        Mockito.when(userPersistencePort.userAlreadyExists(userModel.getDocument()))
                .thenReturn(false);
        Mockito.when(userPersistencePort.mailAlreadyExists(userModel.getEmail()))
                .thenReturn(false);
        userModel.setPassword(passwordEncrypted);
        Mockito.doNothing().when(userPersistencePort).registerUser(userModel);
        userUseCase.registerUser(userModel);

        //Then
        Mockito.verify(userPersistencePort).registerUser(userModel);
    }

    @Test
    void shouldThrowNullPointerExceptionInRegisterUser() {
        //Then
        assertThrows(NullPointerException.class, () -> userUseCase.registerUser(null));
    }

    @Test
    void shouldThrowValidationModelExceptionInRegisterUser() {
        //Given
        UserModel userModel = UseCaseDataFactory.getUserModelEmpty();

        //Then
        assertThrows(NoDataFoundException.class, () -> userUseCase.registerUser(userModel));
    }

    @Test
    void shouldThrowUserAlreadyExistsExceptionInRegisterUser() {
        //Given
        UserModel userModel = UseCaseDataFactory.getUserAdminWithSetters();

        //When
        Mockito.when(userPersistencePort.userAlreadyExists(userModel.getDocument()))
                .thenReturn(true);

        //Then
        assertThrows(UserAlreadyExistsException.class, () -> userUseCase.registerUser(userModel));
    }
    @Test
    void shouldThrowMailAlreadyExistsExceptionInRegisterUser() {
        //Given
        UserModel userModel = UseCaseDataFactory.getUserAdminWithSetters();

        //When
        Mockito.when(userPersistencePort.mailAlreadyExists(userModel.getEmail()))
                .thenReturn(true);

        //Then
        assertThrows(UserAlreadyExistsException.class, () -> userUseCase.registerUser(userModel));
    }*/
}
