package com.example.emazon_aux.infrastructure;

import com.example.emazon_aux.aplication.dto.request.AuthCredentials;
import com.example.emazon_aux.aplication.handler.AuthHandler;
import com.example.emazon_aux.infrastructure.factory.AuthControllerTestDataFactory;
import com.example.emazon_aux.infrastructure.input.AuthController;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    AuthController authController;

    @Mock
    AuthHandler authHandler;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .build();
    }

    @Test
    void shouldLoginUser() throws Exception {
        //Given
        AuthCredentials authCredentials =
                AuthControllerTestDataFactory.getLoginRequest();

        mockMvc.perform(post("/auth/login")
                        .content(AuthControllerTestDataFactory
                                .asJsonString(authCredentials))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


}
