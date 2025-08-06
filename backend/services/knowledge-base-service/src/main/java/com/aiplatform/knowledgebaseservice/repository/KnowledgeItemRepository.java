package com.aiplatform.knowledgebaseservice.repository;

import com.aiplatform.knowledgebaseservice.model.KnowledgeItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeItemRepository extends JpaRepository<KnowledgeItem, Long> {
}