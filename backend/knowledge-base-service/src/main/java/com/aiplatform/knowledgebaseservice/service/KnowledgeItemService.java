package com.aiplatform.knowledgebaseservice.service;

import com.aiplatform.knowledgebaseservice.model.KnowledgeItem;
import com.aiplatform.knowledgebaseservice.repository.KnowledgeItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeItemService {
    @Autowired
    private KnowledgeItemRepository repository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "knowledge-events";

    public List<KnowledgeItem> getAllItems() {
        return repository.findAll();
    }

    public Optional<KnowledgeItem> getItemById(Long id) {
        return repository.findById(id);
    }

    public KnowledgeItem createItem(KnowledgeItem item) {
        KnowledgeItem saved = repository.save(item);
        kafkaTemplate.send(TOPIC, "Knowledge item created: " + saved.getId());
        return saved;
    }

    public void deleteItem(Long id) {
        repository.deleteById(id);
        kafkaTemplate.send(TOPIC, "Knowledge item deleted: " + id);
    }
}