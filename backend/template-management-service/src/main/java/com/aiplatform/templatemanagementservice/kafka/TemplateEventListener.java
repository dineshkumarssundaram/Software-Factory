package com.aiplatform.templatemanagementservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TemplateEventListener {
    @KafkaListener(topics = "template-events", groupId = "template-management-service-group")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Received event: " + record.value());
    }
}