package com.aiplatform.notificationservice.service;

import com.aiplatform.notificationservice.model.Notification;
import com.aiplatform.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository repository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "notification-events";

    public List<Notification> getAllNotifications() {
        return repository.findAll();
    }

    public Optional<Notification> getNotificationById(Long id) {
        return repository.findById(id);
    }

    public Notification createNotification(Notification notification) {
        Notification saved = repository.save(notification);
        kafkaTemplate.send(TOPIC, "Notification created: " + saved.getId());
        return saved;
    }

    public void deleteNotification(Long id) {
        repository.deleteById(id);
        kafkaTemplate.send(TOPIC, "Notification deleted: " + id);
    }
}