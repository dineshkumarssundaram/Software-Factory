package com.aiplatform.cicdpipelineservice.repository;

import com.aiplatform.cicdpipelineservice.model.Pipeline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PipelineRepository extends JpaRepository<Pipeline, Long> {
}