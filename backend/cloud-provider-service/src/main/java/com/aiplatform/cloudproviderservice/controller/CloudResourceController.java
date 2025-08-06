package com.aiplatform.cloudproviderservice.controller;

import com.aiplatform.cloudproviderservice.model.CloudResource;
import com.aiplatform.cloudproviderservice.service.CloudResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloud-resources")
public class CloudResourceController {
    @Autowired
    private CloudResourceService service;

    @GetMapping
    public List<CloudResource> getAllResources() {
        return service.getAllResources();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CloudResource> getResourceById(@PathVariable Long id) {
        return service.getResourceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CloudResource createResource(@RequestBody CloudResource resource) {
        return service.createResource(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        service.deleteResource(id);
        return ResponseEntity.noContent().build();
    }
}