package com.furkan.exam_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;  // Hangi öğrenciye ait
    private int courseId;   // Ders adı
    private Double grade;    // Not
    private String status;   // PASSED, FAILED, PENDING
}