package com.furkan.studentservice.dto;

import lombok.Data;

@Data
public class ExamResultDTO {
    private Long id;
    private Integer courseId;
    private Double grade;
    private String status;
}