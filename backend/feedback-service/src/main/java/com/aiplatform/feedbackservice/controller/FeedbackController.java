package com.aiplatform.feedbackservice.controller;

import com.aiplatform.feedbackservice.model.Feedback;
import com.aiplatform.feedbackservice.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService service;

    @GetMapping
    public List<Feedback> getAllFeedback() {
        return service.getAllFeedback();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        return service.getFeedbackById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Feedback createFeedback(@RequestBody Feedback feedback) {
        return service.createFeedback(feedback);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        service.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}