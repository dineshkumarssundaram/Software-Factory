package com.aiplatform.deploymentservice.service;

import com.aiplatform.deploymentservice.model.Deployment;
import com.aiplatform.deploymentservice.repository.DeploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeploymentService {
    @Autowired
    private DeploymentRepository repository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "deployment-events";

    public List<Deployment> getAllDeployments() {
        return repository.findAll();
    }

    public Optional<Deployment> getDeploymentById(Long id) {
        return repository.findById(id);
    }

    public Deployment createDeployment(Deployment deployment) {
        Deployment saved = repository.save(deployment);
        kafkaTemplate.send(TOPIC, "Deployment created: " + saved.getId());
        return saved;
    }

    public void deleteDeployment(Long id) {
        repository.deleteById(id);
        kafkaTemplate.send(TOPIC, "Deployment deleted: " + id);
    }
}