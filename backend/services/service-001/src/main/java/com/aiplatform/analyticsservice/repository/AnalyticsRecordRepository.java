package com.aiplatform.analyticsservice.repository;

import com.aiplatform.analyticsservice.model.AnalyticsRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalyticsRecordRepository extends JpaRepository<AnalyticsRecord, Long> {
}