package com.aiplatform.projectservice.repository;

import com.aiplatform.projectservice.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}