package com.aiplatform.userservice.service;

import com.aiplatform.userservice.model.User;
import com.aiplatform.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "user-events";

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        User saved = userRepository.save(user);
        kafkaTemplate.send(TOPIC, "User created: " + saved.getId());
        return saved;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        kafkaTemplate.send(TOPIC, "User deleted: " + id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findAll().stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    public Optional<User> updateProfile(String username, User updated) {
        return findByUsername(username).map(user -> {
            user.setEmail(updated.getEmail());
            user.setRole(updated.getRole());
            return userRepository.save(user);
        });
    }
}