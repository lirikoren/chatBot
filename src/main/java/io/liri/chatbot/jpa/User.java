package io.liri.chatbot.jpa;

import io.liri.chatbot.openAiChatbot.model.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    private String username;
    private String password;
    private Gender gender;
    @Id
    private Long id;

    public User() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
