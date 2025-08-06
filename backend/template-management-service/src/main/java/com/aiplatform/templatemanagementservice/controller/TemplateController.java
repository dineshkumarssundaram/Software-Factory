package com.aiplatform.templatemanagementservice.controller;

import com.aiplatform.templatemanagementservice.model.Template;
import com.aiplatform.templatemanagementservice.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
public class TemplateController {
    @Autowired
    private TemplateService service;

    @GetMapping
    public List<Template> getAllTemplates() {
        return service.getAllTemplates();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Template> getTemplateById(@PathVariable Long id) {
        return service.getTemplateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Template createTemplate(@RequestBody Template template) {
        return service.createTemplate(template);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable Long id) {
        service.deleteTemplate(id);
        return ResponseEntity.noContent().build();
    }
}