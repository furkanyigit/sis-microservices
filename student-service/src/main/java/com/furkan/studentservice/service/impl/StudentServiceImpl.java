package com.furkan.studentservice.service.impl;

import com.furkan.studentservice.client.ExamServiceClient;
import com.furkan.studentservice.dto.ExamResultDTO;
import com.furkan.studentservice.dto.StudentDto;
import com.furkan.studentservice.entity.Student;
import com.furkan.studentservice.mapper.StudentMapper; // Mapper'ı import etmeyi unutma
import com.furkan.studentservice.repository.StudentRepository;
import com.furkan.studentservice.service.StudentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ExamServiceClient examServiceClient;
    private final StudentMapper studentMapper; // MapStruct arayüzü

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              ExamServiceClient examServiceClient,
                              StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.examServiceClient = examServiceClient;
        this.studentMapper = studentMapper;
    }

    // --- CREATE ---
    @Override
    public StudentDto createStudent(StudentDto dto) {
        // Eski convertToEntity yerine mapper kullanıyoruz
        Student student = studentMapper.toEntity(dto);
        Student savedStudent = studentRepository.save(student);
        // Eski convertToDto yerine mapper kullanıyoruz
        return studentMapper.toDto(savedStudent);
    }

    // --- UPDATE (TEMİZLENMİŞ) ---
    @Override
    public StudentDto updateStudent(Long id, StudentDto dto) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

        // TEK SATIRDA GÜNCELLEME: O uzun set set set kodları gitti
        studentMapper.updateStudentFromDto(dto, existing);

        Student updatedStudent = studentRepository.save(existing);
        return studentMapper.toDto(updatedStudent);
    }

    // --- DELETE ---
    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }


    // --- GET BY ID ---
    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        return studentMapper.toDto(student);
    }

    // --- LIST ALL ---
    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto) // Burada da mapper kullanılıyor
                .collect(Collectors.toList());
    }

    // --- PAGINATION ---
    @Override
    public Page<Student> paginationStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public List<Long> getAllStudentIds() {
        return studentRepository.findAllIds();
    }

    // --- ÖĞRENCİ + NOTLAR ---
    @Override
    public Map<String, Object> getStudentWithExamResults(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı: " + studentId));

        List<ExamResultDTO> examGrades = examServiceClient.getExamsByStudentId(studentId);

        Map<String, Object> response = new HashMap<>();
        response.put("studentInfo", student);
        response.put("examGrades", examGrades);

        return response;
    }

    // --- FALLBACK METOTLARI ---
    public List<StudentDto> getAllStudentsFallback(Throwable t) {
        StudentDto errorStudent = new StudentDto();
        errorStudent.setFirstName("Servis");
        errorStudent.setLastName("Yavaşladı/Kapalı");
        errorStudent.setEmail("Lütfen bekleyin...");
        return List.of(errorStudent);
    }

    public Page<Student> paginationStudentsFallback(Pageable pageable, Throwable t) {
        return Page.empty(pageable);
    }

    public StudentDto getStudentByIdFallback(Long id, Throwable t) {
        StudentDto fallbackDto = new StudentDto();
        fallbackDto.setId(id);
        fallbackDto.setFirstName("N/A");
        fallbackDto.setLastName("Service Unavailable");
        return fallbackDto;
    }
}