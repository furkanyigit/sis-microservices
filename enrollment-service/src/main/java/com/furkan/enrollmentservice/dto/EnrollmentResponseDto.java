package com.furkan.enrollmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponseDto {
    private Long id;
    private Long studentId;
    private String course;
    private String semester;
    private String status;
}
