package com.aiplatform.notificationservice.repository;

import com.aiplatform.notificationservice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}