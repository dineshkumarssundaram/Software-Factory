package com.aiplatform.cloudproviderservice.service;

import com.aiplatform.cloudproviderservice.model.CloudResource;
import com.aiplatform.cloudproviderservice.repository.CloudResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CloudResourceService {
    @Autowired
    private CloudResourceRepository repository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "cloud-resource-events";

    public List<CloudResource> getAllResources() {
        return repository.findAll();
    }

    public Optional<CloudResource> getResourceById(Long id) {
        return repository.findById(id);
    }

    public CloudResource createResource(CloudResource resource) {
        CloudResource saved = repository.save(resource);
        kafkaTemplate.send(TOPIC, "Cloud resource created: " + saved.getId());
        return saved;
    }

    public void deleteResource(Long id) {
        repository.deleteById(id);
        kafkaTemplate.send(TOPIC, "Cloud resource deleted: " + id);
    }
}