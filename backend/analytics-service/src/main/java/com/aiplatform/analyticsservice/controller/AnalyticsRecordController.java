package com.aiplatform.analyticsservice.controller;

import com.aiplatform.analyticsservice.model.AnalyticsRecord;
import com.aiplatform.analyticsservice.service.AnalyticsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analytics-records")
public class AnalyticsRecordController {
    @Autowired
    private AnalyticsRecordService service;

    @GetMapping
    public List<AnalyticsRecord> getAllRecords() {
        return service.getAllRecords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalyticsRecord> getRecordById(@PathVariable Long id) {
        return service.getRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AnalyticsRecord createRecord(@RequestBody AnalyticsRecord record) {
        return service.createRecord(record);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        service.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}