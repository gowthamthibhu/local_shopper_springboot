package com.localshopper.backend.dto;

import com.localshopper.backend.Model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private Long userId;
    private String name;
    private UserRole role;
}
