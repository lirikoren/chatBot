package io.liri.chatbot.security;

import groovy.lang.Singleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlerControllerTest {
    @InjectMocks
    ExceptionHandlerController exceptionHandlerController;

    @BeforeEach
    void setUp() {


    }

    @Test
    void badCredentialsException() {
        WebRequest webRequest = mock(WebRequest.class);
        BadCredentialsException exception = mock(BadCredentialsException.class);
        doReturn("").when(exception).getMessage();
        doReturn(Collections.emptyMap()).when(webRequest).getParameterMap();
        ResponseEntity<String> responseEntity = exceptionHandlerController.badCredentialsException(exception, webRequest);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("", responseEntity.getBody());


    }

    @Test
    void handleValidationExceptions() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("user", "username", "message");
        doReturn(bindingResult).when(exception).getBindingResult();
        doReturn(Collections.singletonList(fieldError)).when(bindingResult).getAllErrors();

        Map<String, String> errors = exceptionHandlerController.handleValidationExceptions(exception);
        assertEquals("message", errors.get("username"));
        assertEquals(1, errors.size());
    }
}