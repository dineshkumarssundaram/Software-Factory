package com.aiplatform.thirdpartyintegrationservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class IntegrationEventListener {
    @KafkaListener(topics = "integration-events", groupId = "third-party-integration-service-group")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Received event: " + record.value());
    }
}