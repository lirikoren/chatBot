package io.liri.chatbot.security.login.controller;

import io.liri.chatbot.security.model.LoginRequest;
import io.liri.chatbot.security.model.LoginResponse;
import io.liri.chatbot.security.login.sevice.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@Validated
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest userLoginRequest) {
        return ResponseEntity.ok(loginService.authenticate(userLoginRequest));
    }
}
