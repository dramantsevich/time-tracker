package com.example.time_tracker.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class WorkRecordDTO {
    Long id;
    Long userId;
    Long projectId;
    BigDecimal hoursWorked;
    LocalDate workDate;
}
