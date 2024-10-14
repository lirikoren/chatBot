package io.liri.chatbot.openAiChatbot.modal;

import java.util.Objects;

public class ChatbotRequest {

    private String request;

    public ChatbotRequest(String request) {
        this.request = request;
    }

    public ChatbotRequest() {
    }

    public String getRequest() {
        return request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatbotRequest that = (ChatbotRequest) o;
        return Objects.equals(request, that.request);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(request);
    }

    @Override
    public String toString() {
        return "ChatBotRequest{" +
                "request='" + request + '\'' +
                '}';
    }
}
