package io.liri.chatbot.register.controller;

import io.liri.chatbot.register.service.UserRegistrationService;
import io.liri.chatbot.users.model.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/register")
@RestController
public class RegisterController {
    Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private UserRegistrationService userRegistrationService;

    public RegisterController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userRegistrationService.registerNewUser(userDto));
    }


}
