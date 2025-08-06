package com.aiplatform.templatemanagementservice.repository;

import com.aiplatform.templatemanagementservice.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {
}