package com.example.time_tracker.controller;

import com.example.time_tracker.dto.CreateWorkRecordDTO;
import com.example.time_tracker.dto.UserDTO;
import com.example.time_tracker.dto.WorkRecordDTO;
import com.example.time_tracker.model.User;
import com.example.time_tracker.service.UserService;
import com.example.time_tracker.service.WorkRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor
public class UserController {
    private final WorkRecordService workRecordService;
    private final UserService userService;

    // Отображение записей
    @GetMapping("/records")
    public List<WorkRecordDTO> getUserRecords() {
        return workRecordService.getUserRecords();
    }

    // Создание записи
    @PostMapping("/records")
    public WorkRecordDTO createRecord(@RequestBody CreateWorkRecordDTO createWorkRecordDTO) {
        return workRecordService.createRecord(createWorkRecordDTO);
    }

    //Обновление записи
    @PutMapping("/records/{id}")
    public WorkRecordDTO updateRecord(@PathVariable Long id, @RequestBody CreateWorkRecordDTO createWorkRecordDTO) {
        return workRecordService.updateRecord(id, createWorkRecordDTO);
    }

    //Удалине записи
    @DeleteMapping("/records/{id}")
    public void deleteRecord(@PathVariable Long id) {
        workRecordService.deleteRecord(id);
    }

    // Получение информации о текущем пользователе
    @GetMapping("/me")
    public UserDTO getCurrentUser(@AuthenticationPrincipal User user) {
        User currentUser = userService.getUserById(user.getId());
        return new UserDTO(currentUser.getId(), currentUser.getUsername(), currentUser.getRole());
    }
}
