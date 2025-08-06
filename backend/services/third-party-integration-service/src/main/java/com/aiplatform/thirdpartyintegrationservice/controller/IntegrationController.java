package com.aiplatform.thirdpartyintegrationservice.controller;

import com.aiplatform.thirdpartyintegrationservice.model.Integration;
import com.aiplatform.thirdpartyintegrationservice.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/integrations")
public class IntegrationController {
    @Autowired
    private IntegrationService service;

    @GetMapping
    public List<Integration> getAllIntegrations() {
        return service.getAllIntegrations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Integration> getIntegrationById(@PathVariable Long id) {
        return service.getIntegrationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Integration createIntegration(@RequestBody Integration integration) {
        return service.createIntegration(integration);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIntegration(@PathVariable Long id) {
        service.deleteIntegration(id);
        return ResponseEntity.noContent().build();
    }
}