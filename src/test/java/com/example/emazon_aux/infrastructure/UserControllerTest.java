package com.example.emazon_aux.infrastructure;


import com.example.emazon_aux.aplication.dto.request.UserRequestDto;
import com.example.emazon_aux.aplication.handler.IUserHandler;
import com.example.emazon_aux.domain.util.constants.Constants;
import com.example.emazon_aux.infrastructure.factory.UserControllerTestDataFactory;
import com.example.emazon_aux.infrastructure.input.UserRestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    UserRestController userRestController;

    @Mock
    IUserHandler userHandler;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
    }

    @Test
    void shouldCreateUser() throws Exception {
        //Given
        UserRequestDto userRequestDto =
                UserControllerTestDataFactory.getUserRequest();

        String token = UserControllerTestDataFactory.createJwtToken("ADMIN", "test@example.com");

        // When //Then
        mockMvc.perform(post("/user/")
                        .header("Authorization", "Bearer " + token)
                        .content(UserControllerTestDataFactory
                                .asJsonString(userRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(Constants.USER_CREATED_MESSAGE));

    }
}
