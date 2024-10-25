package com.example.time_tracker.controller;

import com.example.time_tracker.dto.CreateProjectDTO;
import com.example.time_tracker.dto.CreateUserDTO;
import com.example.time_tracker.dto.ProjectDTO;
import com.example.time_tracker.dto.UserDTO;
import com.example.time_tracker.service.ProjectService;
import com.example.time_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final ProjectService projectService;

    // Отображение всех пользователей
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Создание пользователся
    @PostMapping("/users")
    public UserDTO createUser(@RequestBody CreateUserDTO createUserDTO) {
        return userService.createUser(createUserDTO);
    }

    // Обновление пользователя
    @PutMapping("/users/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody CreateUserDTO createUserDTO) {
        return userService.updateUser(id, createUserDTO);
    }

    // Удаление пользователя
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Отображение всех проектов
    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    //Создание проекта
    @PostMapping("/projects")
    public ProjectDTO createProject(@RequestBody CreateProjectDTO createProjectDTO) {
        return projectService.createProject(createProjectDTO);
    }

    // Удаление проекта
    @DeleteMapping("/projects/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}