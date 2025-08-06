package com.aiplatform.projectservice.service;

import com.aiplatform.projectservice.model.Project;
import com.aiplatform.projectservice.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private RestTemplate restTemplate;

    private static final String TOPIC = "project-events";

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project createProject(Project project) {
        Project saved = projectRepository.save(project);
        kafkaTemplate.send(TOPIC, "Project created: " + saved.getId());
        // Example inter-service REST call to user-service
        String userServiceUrl = "http://user-service/users/" + saved.getUserId();
        try {
            String user = restTemplate.getForObject(userServiceUrl, String.class);
            System.out.println("User info: " + user);
        } catch (Exception e) {
            System.out.println("User service not available: " + e.getMessage());
        }
        return saved;
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
        kafkaTemplate.send(TOPIC, "Project deleted: " + id);
    }
}