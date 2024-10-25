package com.example.time_tracker.service;

import com.example.time_tracker.dto.CreateUserDTO;
import com.example.time_tracker.dto.UserDTO;
import com.example.time_tracker.model.Role;
import com.example.time_tracker.model.User;
import com.example.time_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToUserDTO)
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public UserDTO createUser(CreateUserDTO createUserDTO) {
        User user = new User();
        user.setUsername(createUserDTO.getUsername());
        user.setPassword(createUserDTO.getPassword());
        user.setRole(createUserDTO.getRole());
        User savedUser = userRepository.save(user);
        return mapToUserDTO(savedUser);
    }

    public UserDTO updateUser(Long id, CreateUserDTO createUserDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(createUserDTO.getUsername());
        user.setRole(createUserDTO.getRole());
        User updatedUser = userRepository.save(user);
        return mapToUserDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    //Получение пользователя по имени пользователя
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    //Получение текущего пользователя
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    private UserDTO mapToUserDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getRole());
    }
}
