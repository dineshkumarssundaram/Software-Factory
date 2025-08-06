package com.aiplatform.cicdpipelineservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PipelineEventListener {
    @KafkaListener(topics = "pipeline-events", groupId = "cicd-pipeline-service-group")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Received event: " + record.value());
    }
}