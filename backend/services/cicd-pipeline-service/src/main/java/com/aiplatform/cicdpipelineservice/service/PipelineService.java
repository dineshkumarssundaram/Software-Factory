package com.aiplatform.cicdpipelineservice.service;

import com.aiplatform.cicdpipelineservice.model.Pipeline;
import com.aiplatform.cicdpipelineservice.repository.PipelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PipelineService {
    @Autowired
    private PipelineRepository repository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "pipeline-events";

    public List<Pipeline> getAllPipelines() {
        return repository.findAll();
    }

    public Optional<Pipeline> getPipelineById(Long id) {
        return repository.findById(id);
    }

    public Pipeline createPipeline(Pipeline pipeline) {
        Pipeline saved = repository.save(pipeline);
        kafkaTemplate.send(TOPIC, "Pipeline created: " + saved.getId());
        return saved;
    }

    public void deletePipeline(Long id) {
        repository.deleteById(id);
        kafkaTemplate.send(TOPIC, "Pipeline deleted: " + id);
    }
}