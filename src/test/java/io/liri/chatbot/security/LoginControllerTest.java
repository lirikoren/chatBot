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
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
@ExtendWith(MockitoExtension.class)
class LoginControllerTest {
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    JwtGenerator jwtGenerator;

    @InjectMocks
    LoginController loginController;

    @BeforeEach
    void setUp() {

    }

    @Test
    void SuccessLogin() {
        LoginRequest loginRequest = new LoginRequest("username", "password");
        String token = "JWTToken";
        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);
        Collection<? extends GrantedAuthority> authorities = mock(Collection.class);

        doReturn(authentication).when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        doReturn(userDetails).when(authentication).getPrincipal();
        doReturn(authorities).when(userDetails).getAuthorities();
        doReturn(token).when(jwtGenerator).jwtGenerator(loginRequest.username(), userDetails.getAuthorities());

        ResponseEntity<LoginResponse> response = loginController.login(loginRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User logged in successfully", response.getBody().message());
        assertEquals(token, response.getBody().token());

    }

    @Test
    void FailureLogin() {
        LoginRequest loginRequest = new LoginRequest("username", "password");
        Authentication authentication = mock(Authentication.class);

        doReturn(authentication).when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        doReturn(null).when(authentication).getPrincipal();

        assertEquals(HttpStatus.UNAUTHORIZED, loginController.login(loginRequest).getStatusCode());

    }
}