package io.liri.chatbot.security;

import io.liri.chatbot.security.jwt.JwtGenerator;
import io.liri.chatbot.security.model.LoginRequest;
import io.liri.chatbot.security.model.LoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    LoginRequest loginRequest;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    JwtGenerator jwtGenerator;

    @InjectMocks
    LoginService loginService;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest("username", "password");
    }

    @Test
    void authenticate() {
        String token = "JWTToken";
        UserDetails userDetails = mock(UserDetails.class);
        Collection authorities = mock(Collection.class);
        Authentication authentication = mock(Authentication.class);

        doReturn(authentication).when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        doReturn(userDetails).when(authentication).getPrincipal();
        doReturn(authorities).when(userDetails).getAuthorities();
        doReturn(token).when(jwtGenerator).jwtGenerator(loginRequest.username(), userDetails.getAuthorities());

        LoginResponse response = loginService.authenticate(loginRequest);
        assertEquals("User logged in successfully", response.message());
        assertEquals(token, response.token());

    }
}