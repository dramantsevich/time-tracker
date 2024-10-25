package com.example.time_tracker.dto;

import com.example.time_tracker.model.Role;
import lombok.Value;

@Value
public class UserDTO {
    Long id;
    String username;
    Role role;
}
