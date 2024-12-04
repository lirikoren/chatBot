package io.liri.chatbot.users.model;

import io.liri.chatbot.openAiChatbot.model.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public record User(
        @Id
        String id,
        @Indexed(unique = true)
        String email,
        String username,
        String password,
        Gender gender) {

}
