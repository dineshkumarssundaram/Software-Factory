package com.aiplatform.cloudproviderservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cloud_resources")
public class CloudResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String provider;
    private String resourceType;
    private String status;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
    public String getResourceType() { return resourceType; }
    public void setResourceType(String resourceType) { this.resourceType = resourceType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}