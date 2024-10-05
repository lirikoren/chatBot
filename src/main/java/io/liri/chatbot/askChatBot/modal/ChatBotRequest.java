package io.liri.chatbot.askChatBot.modal;

import java.util.Objects;

public class ChatBotRequest {

    private String request;

    public ChatBotRequest(String request) {
        this.request = request;
    }

    public ChatBotRequest() {
    }

    public String getRequest() {
        return request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatBotRequest that = (ChatBotRequest) o;
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
