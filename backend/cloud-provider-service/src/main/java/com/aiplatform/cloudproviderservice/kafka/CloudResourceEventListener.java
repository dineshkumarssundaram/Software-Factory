package com.aiplatform.cloudproviderservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CloudResourceEventListener {
    @KafkaListener(topics = "cloud-resource-events", groupId = "cloud-provider-service-group")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Received event: " + record.value());
    }
}