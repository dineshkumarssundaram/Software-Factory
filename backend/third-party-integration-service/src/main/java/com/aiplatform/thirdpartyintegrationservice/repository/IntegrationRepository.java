package com.aiplatform.thirdpartyintegrationservice.repository;

import com.aiplatform.thirdpartyintegrationservice.model.Integration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntegrationRepository extends JpaRepository<Integration, Long> {
}