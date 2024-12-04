package io.liri.chatbot.users.model;

import io.liri.chatbot.openAiChatbot.model.Gender;

public record UserDto(String userName, String plainTextPassword, Gender gender, String email) {
    //add validations
}
