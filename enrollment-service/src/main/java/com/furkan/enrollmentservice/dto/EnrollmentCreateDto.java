package com.furkan.enrollmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentCreateDto {
    private Long studentId;   // Hangi öğrenci
    private String course;    // Ders adı
    private String semester;  // Örn: Fall2026
    private String status;    // ENROLLED, CANCELLED, COMPLETED (opsiyonel, default ENROLLED)
}
