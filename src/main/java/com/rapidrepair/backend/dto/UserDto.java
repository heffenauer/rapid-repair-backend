package com.rapidrepair.backend.dto;

import com.rapidrepair.backend.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private UserRole role;
}
