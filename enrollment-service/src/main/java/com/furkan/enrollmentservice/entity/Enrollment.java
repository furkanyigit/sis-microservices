package com.furkan.enrollmentservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId; // Hangi öğrenci

    private String course;  // Ders adı

    private String semester; // Örn: Fall2026

    private String status;   // ENROLLED, CANCELLED, COMPLETED
}