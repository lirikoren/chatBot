package io.liri.chatbot.jpa;

import io.liri.chatbot.openAiChatbot.model.Gender;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("chatbot");

    public void createUser(String username, String password , Gender gender) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setGender(gender);

        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }
}
