package com.furkan.exam_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamCreateDto {
    private Long studentId;   // Öğrenci ID
    private String course;    // Ders adı
    private Double grade;     // Not
    private String status;    // PASSED, FAILED, PENDING (opsiyonel, default PENDING)
}