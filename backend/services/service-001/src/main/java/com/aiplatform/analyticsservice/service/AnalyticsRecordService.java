package com.aiplatform.analyticsservice.service;

import com.aiplatform.analyticsservice.model.AnalyticsRecord;
import com.aiplatform.analyticsservice.repository.AnalyticsRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnalyticsRecordService {
    @Autowired
    private AnalyticsRecordRepository repository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "analytics-events";

    public List<AnalyticsRecord> getAllRecords() {
        return repository.findAll();
    }

    public Optional<AnalyticsRecord> getRecordById(Long id) {
        return repository.findById(id);
    }

    public AnalyticsRecord createRecord(AnalyticsRecord record) {
        AnalyticsRecord saved = repository.save(record);
        kafkaTemplate.send(TOPIC, "Analytics record created: " + saved.getId());
        return saved;
    }

    public void deleteRecord(Long id) {
        repository.deleteById(id);
        kafkaTemplate.send(TOPIC, "Analytics record deleted: " + id);
    }
}