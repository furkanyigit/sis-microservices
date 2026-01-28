package com.furkan.enrollmentservice.controller;


import com.furkan.enrollmentservice.dto.EnrollmentCreateDto;
import com.furkan.enrollmentservice.dto.EnrollmentResponseDto;
import com.furkan.enrollmentservice.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDto> enrollStudent(@RequestBody EnrollmentCreateDto dto) {
        EnrollmentResponseDto created = enrollmentService.enrollStudent(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDto>> getAllEnrollments() {
        List<EnrollmentResponseDto> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponseDto>> getEnrollmentsByStudent(@PathVariable Long studentId) {
        List<EnrollmentResponseDto> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/course/{course}")
    public ResponseEntity<List<EnrollmentResponseDto>> getEnrollmentsByCourse(@PathVariable String course) {
        List<EnrollmentResponseDto> enrollments = enrollmentService.getEnrollmentsByCourse(course);
        return ResponseEntity.ok(enrollments);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<EnrollmentResponseDto> updateEnrollmentStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        EnrollmentResponseDto updated = enrollmentService.updateEnrollmentStatus(id, status);
        return ResponseEntity.ok(updated);
    }
}
