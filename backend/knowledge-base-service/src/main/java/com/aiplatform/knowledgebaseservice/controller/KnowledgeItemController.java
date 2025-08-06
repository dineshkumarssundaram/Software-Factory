package com.aiplatform.knowledgebaseservice.controller;

import com.aiplatform.knowledgebaseservice.model.KnowledgeItem;
import com.aiplatform.knowledgebaseservice.service.KnowledgeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/knowledge-items")
public class KnowledgeItemController {
    @Autowired
    private KnowledgeItemService service;

    @GetMapping
    public List<KnowledgeItem> getAllItems() {
        return service.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KnowledgeItem> getItemById(@PathVariable Long id) {
        return service.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public KnowledgeItem createItem(@RequestBody KnowledgeItem item) {
        return service.createItem(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}