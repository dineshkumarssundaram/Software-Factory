package com.aiplatform.feedbackservice.service;

import com.aiplatform.feedbackservice.model.Feedback;
import com.aiplatform.feedbackservice.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository repository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "feedback-events";

    public List<Feedback> getAllFeedback() {
        return repository.findAll();
    }

    public Optional<Feedback> getFeedbackById(Long id) {
        return repository.findById(id);
    }

    public Feedback createFeedback(Feedback feedback) {
        Feedback saved = repository.save(feedback);
        kafkaTemplate.send(TOPIC, "Feedback created: " + saved.getId());
        return saved;
    }

    public void deleteFeedback(Long id) {
        repository.deleteById(id);
        kafkaTemplate.send(TOPIC, "Feedback deleted: " + id);
    }
}