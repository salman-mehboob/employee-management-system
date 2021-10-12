package com.econception.employemanagement.dto;

import lombok.Data;

@Data
public class TasksDTO {
    private Long employee_id;
    private String dueDate;
    private String subDate;
    private String text;
    private String description;
    private Long taskId;
}
