package io.liri.chatbot.security.login.sevice;

import io.liri.chatbot.users.model.User;
import io.liri.chatbot.users.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class MongoUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MongoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userRepository.findByEmail(username)
               .map(this::buildUserDetails)
               .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private UserDetails buildUserDetails(User user) {
        return withUsername(user.username())
                .password(user.password())
                .roles(String.valueOf(user.gender()))
                .build();
    }
}
