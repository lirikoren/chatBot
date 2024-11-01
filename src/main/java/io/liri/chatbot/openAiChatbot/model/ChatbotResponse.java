package io.liri.chatbot.openAiChatbot.model;

import java.util.Objects;

public class ChatbotResponse {
    private String question;
    private String answer;

    public ChatbotResponse(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public ChatbotResponse() {
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "ChatbotResponse{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatbotResponse that = (ChatbotResponse) o;
        return Objects.equals(question, that.question) && Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }

}
