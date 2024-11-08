package io.liri.chatbot.security;

import io.liri.chatbot.security.jwt.JwtGenerator;
import io.liri.chatbot.security.model.LoginRequest;
import io.liri.chatbot.security.model.LoginResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@Validated
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController(AuthenticationManager authenticationManager, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest userLoginRequest) {
        Authentication authentication = buildAuthentication(userLoginRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            logger.info("Token requested for user :{}", userDetails);
            String token = jwtGenerator.jwtGenerator(userLoginRequest.username(), userDetails.getAuthorities());
            LoginResponse response = new LoginResponse("User logged in successfully", token);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private Authentication buildAuthentication(LoginRequest userLoginRequest) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginRequest.username(),
                userLoginRequest.password()));
    }
}
