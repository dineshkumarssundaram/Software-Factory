package com.aiplatform.userservice.repository;

import com.aiplatform.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}