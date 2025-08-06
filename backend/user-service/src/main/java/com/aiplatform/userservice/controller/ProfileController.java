package com.aiplatform.userservice.controller;

import com.aiplatform.userservice.model.User;
import com.aiplatform.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/profile")
public class ProfileController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<User> getProfile(Authentication authentication) {
        String username = authentication.getName();
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<User> updateProfile(Authentication authentication, @RequestBody User updated) {
        String username = authentication.getName();
        return userService.updateProfile(username, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}