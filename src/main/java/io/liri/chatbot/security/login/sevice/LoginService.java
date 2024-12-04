package io.liri.chatbot.security.login.sevice;

import io.liri.chatbot.security.jwt.JwtGenerator;
import io.liri.chatbot.security.model.LoginRequest;
import io.liri.chatbot.security.model.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    Logger logger = LoggerFactory.getLogger(LoginService.class);
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;

    public LoginService(AuthenticationManager authenticationManager, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }

    private Authentication buildAuthentication(LoginRequest userLoginRequest) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginRequest.username(),
                userLoginRequest.password()));
    }

    public LoginResponse authenticate(LoginRequest userLoginRequest) {
        Authentication authentication = buildAuthentication(userLoginRequest);
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            logger.info("Token requested for user :{}", userDetails);
            String token = jwtGenerator.jwtGenerator(userLoginRequest.username(), userDetails.getAuthorities());
            return new LoginResponse("User logged in successfully", token);
        }
        throw new BadCredentialsException("Invalid username or password");
    }

}
