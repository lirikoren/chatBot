package io.liri.chatbot.register.service;

import io.liri.chatbot.users.model.User;
import io.liri.chatbot.users.model.UserDto;
import io.liri.chatbot.users.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerNewUser(UserDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.plainTextPassword());
        User user = new User(null, userDto.email(), userDto.userName(), encodedPassword, userDto.gender());
        User savedUser = userRepository.save(user);
        return savedUser.id();
    }
}
