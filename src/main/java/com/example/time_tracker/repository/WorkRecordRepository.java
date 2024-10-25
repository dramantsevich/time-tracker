package com.example.time_tracker.repository;

import com.example.time_tracker.model.WorkRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRecordRepository extends JpaRepository<WorkRecord, Long> {
    List<WorkRecord> findByUserId(Long userId);
}