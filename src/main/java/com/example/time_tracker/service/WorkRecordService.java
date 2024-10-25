package com.example.time_tracker.service;


import com.example.time_tracker.dto.CreateWorkRecordDTO;
import com.example.time_tracker.dto.WorkRecordDTO;
import com.example.time_tracker.model.Project;
import com.example.time_tracker.model.WorkRecord;
import com.example.time_tracker.repository.ProjectRepository;
import com.example.time_tracker.repository.WorkRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkRecordService {
    private final WorkRecordRepository workRecordRepository;
    private final UserService userService;
    private final ProjectRepository projectRepository;

    public WorkRecordDTO createRecord(CreateWorkRecordDTO createWorkRecordDTO) {
        Project project = projectRepository.findById(createWorkRecordDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        WorkRecord workRecord = new WorkRecord();
        workRecord.setUser(userService.getCurrentUser());
        workRecord.setProject(project);
        workRecord.setHoursWorked(createWorkRecordDTO.getHoursWorked());
        workRecord.setWorkDate(createWorkRecordDTO.getWorkDate());

        WorkRecord savedRecord = workRecordRepository.save(workRecord);
        return mapToWorkRecordDTO(savedRecord);
    }

    public WorkRecordDTO updateRecord(Long id, CreateWorkRecordDTO createWorkRecordDTO) {
        // Получаем существующий WorkRecord по ID
        WorkRecord workRecord = workRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WorkRecord not found"));

        // Получаем существующий проект из базы данных по ID
        Project project = projectRepository.findById(createWorkRecordDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Обновляем поля WorkRecord
        workRecord.setProject(project); // Устанавливаем существующий проект
        workRecord.setHoursWorked(createWorkRecordDTO.getHoursWorked());
        workRecord.setWorkDate(createWorkRecordDTO.getWorkDate());

        // Сохраняем обновленный WorkRecord
        WorkRecord updatedRecord = workRecordRepository.save(workRecord);
        return mapToWorkRecordDTO(updatedRecord);
    }

    public void deleteRecord(Long id) {
        workRecordRepository.deleteById(id);
    }

    public List<WorkRecordDTO> getUserRecords() {
        // Получить записи текущего пользователя
        List<WorkRecord> records = workRecordRepository.findByUserId(userService.getCurrentUser().getId());
        return records.stream().map(this::mapToWorkRecordDTO).collect(Collectors.toList());
    }

    private WorkRecordDTO mapToWorkRecordDTO(WorkRecord record) {
        return new WorkRecordDTO(record.getId(),
                record.getUser().getId(),
                record.getProject().getId(),
                record.getHoursWorked(),
                record.getWorkDate());
    }
}
