package io.liri.chatbot.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlerControllerTest {

    @InjectMocks
    ExceptionHandlerController exceptionHandlerController;

    @BeforeEach
    void setUp() {
        WebRequest webRequest = mock(WebRequest.class);
        BadCredentialsException badCredentialsException = mock(BadCredentialsException.class);
    }

    @Test
    void badCredentialsException() {

    }

    @Test
    void handleValidationExceptions() {
    }
}