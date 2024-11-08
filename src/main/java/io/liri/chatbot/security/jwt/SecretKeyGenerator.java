package io.liri.chatbot.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Configuration
public class SecretKeyGenerator {

    @Value("classpath:/security/secret.txt")
    private Resource secretFile;

    public byte[] getSecret() throws IOException {
        return secretFile.getContentAsByteArray();
    }
}
