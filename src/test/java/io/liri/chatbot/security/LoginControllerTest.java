package io.liri.chatbot.security;

import io.liri.chatbot.security.jwt.JwtGenerator;
import io.liri.chatbot.security.model.LoginRequest;
import io.liri.chatbot.security.model.LoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

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