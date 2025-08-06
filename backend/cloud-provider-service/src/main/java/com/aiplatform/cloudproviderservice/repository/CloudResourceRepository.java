package com.aiplatform.cloudproviderservice.repository;

import com.aiplatform.cloudproviderservice.model.CloudResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CloudResourceRepository extends JpaRepository<CloudResource, Long> {
}