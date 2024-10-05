package io.liri.chatbot.askChatBot.modal;

import java.util.Objects;

public class ChatBotResponse {
    private String question;
    private String answer;

    public ChatBotResponse(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public ChatBotResponse() {
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "ChatBotResponse{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatBotResponse that = (ChatBotResponse) o;
        return Objects.equals(question, that.question) && Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }
}
