package com.hoainhi.sportfields.dto;

import com.hoainhi.sportfields.enums.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AccountDTO {

    @NotEmpty(message = "User name cannot be empty")
    private String name_user;

    @NotEmpty(message = "User name cannot be empty")
    private String full_name;

    @NotEmpty(message = "Email cannot be empty")
    private String email;


    private String phone;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotEmpty(message = "Confirm password cannot be empty")
    private String confirmPassword;

    private Role role;

}
