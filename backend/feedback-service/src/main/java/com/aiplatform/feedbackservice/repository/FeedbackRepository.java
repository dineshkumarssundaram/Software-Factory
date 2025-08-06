package com.aiplatform.feedbackservice.repository;

import com.aiplatform.feedbackservice.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}