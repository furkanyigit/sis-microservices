package com.furkan.exam_service.controller;

import com.furkan.exam_service.dto.ExamCreateDto;
import com.furkan.exam_service.dto.ExamResponseDto;
import com.furkan.exam_service.service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    // Yeni sınav notu ekle
    @PostMapping
    public ResponseEntity<ExamResponseDto> createExam(@RequestBody ExamCreateDto examCreateDto) {
        ExamResponseDto created = examService.createExam(examCreateDto);
        return ResponseEntity.ok(created);
    }

    // Tüm sınavları listele
    @GetMapping
    public ResponseEntity<List<ExamResponseDto>> getAllExams() {
        List<ExamResponseDto> exams = examService.getAllExams();
        return ResponseEntity.ok(exams);
    }

    // ID’ye göre sınav notunu getir
    @GetMapping("/{id}")
    public ResponseEntity<ExamResponseDto> getExamById(@PathVariable Long id) {
        ExamResponseDto exam = examService.getExamById(id);
        return ResponseEntity.ok(exam);
    }

    // Öğrencinin tüm sınavlarını getir
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ExamResponseDto>> getExamsByStudentId(@PathVariable Long studentId) {
        List<ExamResponseDto> exams = examService.getExamsByStudentId(studentId);
        return ResponseEntity.ok(exams);
    }

    // Sınav notunu güncelle
    @PutMapping("/{id}/grade")
    public ResponseEntity<ExamResponseDto> updateExamGrade(
            @PathVariable Long id,
            @RequestParam Double grade
    ) {
        ExamResponseDto updated = examService.updateExamGrade(id, grade);
        return ResponseEntity.ok(updated);
    }
}
