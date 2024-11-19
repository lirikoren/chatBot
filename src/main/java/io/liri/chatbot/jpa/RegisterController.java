package io.liri.chatbot.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class RegisterController {
    Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    public void register() {

    }

}
