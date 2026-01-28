package com.furkan.enrollmentservice.service;

import com.furkan.enrollmentservice.dto.EnrollmentCreateDto;
import com.furkan.enrollmentservice.dto.EnrollmentResponseDto;

import java.util.List;

public interface EnrollmentService {

    EnrollmentResponseDto enrollStudent(EnrollmentCreateDto dto);
    List<EnrollmentResponseDto> getAllEnrollments();
    List<EnrollmentResponseDto> getEnrollmentsByStudentId(Long studentId);
    List<EnrollmentResponseDto> getEnrollmentsByCourse(String course);
    EnrollmentResponseDto updateEnrollmentStatus(Long id, String status);
}
