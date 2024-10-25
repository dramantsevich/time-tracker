package com.example.time_tracker.service;

import com.example.time_tracker.dto.CreateProjectDTO;
import com.example.time_tracker.dto.ProjectDTO;
import com.example.time_tracker.model.Project;
import com.example.time_tracker.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectDTO createProject(CreateProjectDTO createProjectDTO) {
        Project project = new Project();
        project.setName(createProjectDTO.getName());
        Project savedProject = projectRepository.save(project);
        return mapToProjectDTO(savedProject);
    }

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::mapToProjectDTO)
                .collect(Collectors.toList());
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    private ProjectDTO mapToProjectDTO(Project project) {
        return new ProjectDTO(project.getId(), project.getName());
    }
}
