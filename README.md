# AI-Powered Software Development Platform

## Overview
This platform automates the software development lifecycle using AI agents, enabling a single user to build complex solutions. It is designed for industry-level scalability and maintainability, following best practices for modern software engineering.

## Monorepo Structure
- `frontend/` - React + Tailwind CSS frontend
- `backend/`
  - `user-service/` - Java Spring Boot microservice
  - `project-service/` - Java Spring Boot microservice
  - `orchestrator-service/` - Java Spring Boot microservice
- `deploy/` - Docker Compose and Kubernetes manifests
- `cicd/` - GitHub Actions workflows

## Environments
- Test
- Development
- Production

## Getting Started
See each folder for setup instructions. This monorepo is structured for easy migration to polyrepo as the project grows.