package com.furkan.exam_service.service;

import com.furkan.exam_service.dto.ExamCreateDto;
import com.furkan.exam_service.dto.ExamResponseDto;

import java.util.List;

public interface ExamService {

    // Yeni sınav notu ekle
    ExamResponseDto createExam(ExamCreateDto examCreateDto);
    // Tüm sınav notlarını getir
    List<ExamResponseDto> getAllExams();
    // ID’ye göre sınav notunu getir
    ExamResponseDto getExamById(Long id);
    // Öğrencinin tüm sınavlarını getir
    List<ExamResponseDto> getExamsByStudentId(Long studentId);
    // Not güncelle
    ExamResponseDto updateExamGrade(Long id, Double grade);
}