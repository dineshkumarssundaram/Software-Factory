package com.aiplatform.deploymentservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "deployments")
public class Deployment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;
    private String status;
    private String environment;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getEnvironment() { return environment; }
    public void setEnvironment(String environment) { this.environment = environment; }
}