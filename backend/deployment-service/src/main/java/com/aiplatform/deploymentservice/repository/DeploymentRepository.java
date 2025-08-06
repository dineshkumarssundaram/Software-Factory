package com.aiplatform.deploymentservice.repository;

import com.aiplatform.deploymentservice.model.Deployment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeploymentRepository extends JpaRepository<Deployment, Long> {
}