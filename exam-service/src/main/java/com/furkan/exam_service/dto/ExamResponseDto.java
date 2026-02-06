package com.furkan.exam_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResponseDto {
    private Long id;          // Exam ID
    private Long studentId;   // Öğrenci ID
    private int courseId;    // Ders adı
    private Double grade;     // Not
    private String status;    // PASSED, FAILED, PENDING
}