package com.furkan.exam_service.service.impl;

import com.furkan.exam_service.dto.ExamCreateDto;
import com.furkan.exam_service.dto.ExamResponseDto;
import com.furkan.exam_service.entity.Exam;
import com.furkan.exam_service.repository.ExamRepository;
import com.furkan.exam_service.service.ExamService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    public ExamServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public ExamResponseDto createExam(ExamCreateDto examCreateDto) {
        Exam exam = Exam.builder()
                .studentId(examCreateDto.getStudentId())
                .courseId(examCreateDto.getCourseId())
                .grade(examCreateDto.getGrade())
                .status(examCreateDto.getStatus() != null ? examCreateDto.getStatus() : "PENDING")
                .build();

        Exam saved = examRepository.save(exam);
        return mapToResponseDto(saved);
    }

    @Override
    public List<ExamResponseDto> getAllExams() {
        return examRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExamResponseDto getExamById(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + id));
        return mapToResponseDto(exam);
    }

    @Override
    public List<ExamResponseDto> getExamsByStudentId(Long studentId) {
        return examRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExamResponseDto updateExamGrade(Long id, Double grade) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + id));
        exam.setGrade(grade);
        Exam updated = examRepository.save(exam);
        return mapToResponseDto(updated);
    }

    // Helper method: Entity → Response DTO
    private ExamResponseDto mapToResponseDto(Exam exam) {
        return new ExamResponseDto(
                exam.getId(),
                exam.getStudentId(),
                exam.getCourseId(),
                exam.getGrade(),
                exam.getStatus()
        );
    }
}