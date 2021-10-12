package com.econception.employemanagement.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PasswordDTO implements Serializable {

    @NotBlank(message = "Current Password can't be empty")
    String currPassword;
    @NotBlank(message = "New Password can't be empty")
    String newPassword;
    @NotBlank(message = "Confirm Password can't be empty")
    String confirmPassword;
}
