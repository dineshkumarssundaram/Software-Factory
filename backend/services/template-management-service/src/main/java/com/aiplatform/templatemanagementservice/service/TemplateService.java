package com.aiplatform.templatemanagementservice.service;

import com.aiplatform.templatemanagementservice.model.Template;
import com.aiplatform.templatemanagementservice.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemplateService {
    @Autowired
    private TemplateRepository repository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "template-events";

    public List<Template> getAllTemplates() {
        return repository.findAll();
    }

    public Optional<Template> getTemplateById(Long id) {
        return repository.findById(id);
    }

    public Template createTemplate(Template template) {
        Template saved = repository.save(template);
        kafkaTemplate.send(TOPIC, "Template created: " + saved.getId());
        return saved;
    }

    public void deleteTemplate(Long id) {
        repository.deleteById(id);
        kafkaTemplate.send(TOPIC, "Template deleted: " + id);
    }
}