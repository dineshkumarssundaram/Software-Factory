package com.aiplatform.thirdpartyintegrationservice.service;

import com.aiplatform.thirdpartyintegrationservice.model.Integration;
import com.aiplatform.thirdpartyintegrationservice.repository.IntegrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntegrationService {
    @Autowired
    private IntegrationRepository repository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "integration-events";

    public List<Integration> getAllIntegrations() {
        return repository.findAll();
    }

    public Optional<Integration> getIntegrationById(Long id) {
        return repository.findById(id);
    }

    public Integration createIntegration(Integration integration) {
        Integration saved = repository.save(integration);
        kafkaTemplate.send(TOPIC, "Integration created: " + saved.getId());
        return saved;
    }

    public void deleteIntegration(Long id) {
        repository.deleteById(id);
        kafkaTemplate.send(TOPIC, "Integration deleted: " + id);
    }
}