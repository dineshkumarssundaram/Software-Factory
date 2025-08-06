package com.aiplatform.deploymentservice.controller;

import com.aiplatform.deploymentservice.model.Deployment;
import com.aiplatform.deploymentservice.service.DeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deployments")
public class DeploymentController {
    @Autowired
    private DeploymentService service;

    @GetMapping
    public List<Deployment> getAllDeployments() {
        return service.getAllDeployments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deployment> getDeploymentById(@PathVariable Long id) {
        return service.getDeploymentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Deployment createDeployment(@RequestBody Deployment deployment) {
        return service.createDeployment(deployment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeployment(@PathVariable Long id) {
        service.deleteDeployment(id);
        return ResponseEntity.noContent().build();
    }
}