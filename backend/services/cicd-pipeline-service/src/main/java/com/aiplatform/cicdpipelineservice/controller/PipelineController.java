package com.aiplatform.cicdpipelineservice.controller;

import com.aiplatform.cicdpipelineservice.model.Pipeline;
import com.aiplatform.cicdpipelineservice.service.PipelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pipelines")
public class PipelineController {
    @Autowired
    private PipelineService service;

    @GetMapping
    public List<Pipeline> getAllPipelines() {
        return service.getAllPipelines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pipeline> getPipelineById(@PathVariable Long id) {
        return service.getPipelineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pipeline createPipeline(@RequestBody Pipeline pipeline) {
        return service.createPipeline(pipeline);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePipeline(@PathVariable Long id) {
        service.deletePipeline(id);
        return ResponseEntity.noContent().build();
    }
}