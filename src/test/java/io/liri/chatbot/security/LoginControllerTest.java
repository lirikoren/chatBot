package io.liri.chatbot.security;

import io.liri.chatbot.security.login.controller.LoginController;
import io.liri.chatbot.security.model.LoginRequest;
import io.liri.chatbot.security.model.LoginResponse;
import io.liri.chatbot.security.login.sevice.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    LoginRequest loginRequest;

    @Mock
    LoginService loginService;

    @InjectMocks
    LoginController loginController;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest("username", "password");
    }

    @Test
    void SuccessLogin() {
        LoginResponse loginResponse = new LoginResponse("message", "token");

        doReturn(loginResponse).when(loginService.authenticate(loginRequest));

    }

    @Test
    void FailureLogin() {

    }
}