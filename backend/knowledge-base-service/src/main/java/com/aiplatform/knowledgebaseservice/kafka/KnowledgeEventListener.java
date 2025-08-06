package com.aiplatform.knowledgebaseservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KnowledgeEventListener {
    @KafkaListener(topics = "knowledge-events", groupId = "knowledge-base-service-group")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Received event: " + record.value());
    }
}