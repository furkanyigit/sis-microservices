package com.furkan.studentservice.service.impl;

import com.furkan.studentservice.dto.StudentDto;
import com.furkan.studentservice.entity.Student;
import com.furkan.studentservice.repository.StudentRepository;
import com.furkan.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // --- CREATE ---
    @Override
    public StudentDto createStudent(StudentDto dto) {
        Student student = convertToEntity(dto);
        Student savedStudent = studentRepository.save(student);
        return convertToDto(savedStudent);
    }

    // --- UPDATE ---
    @Override
    public StudentDto updateStudent(Long id, StudentDto dto) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));


        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        existing.setDateOfBirth(dto.getDateOfBirth());
        existing.setGender(dto.getGender());
        existing.setDepartmentId(dto.getDepartmentId());
        existing.setClassNo(dto.getClassNo());
        existing.setEnrollmentDate(dto.getEnrollmentDate());
        existing.setIsActive(dto.getIsActive());
        existing.setStudentNumber(dto.getStudentNumber());

        Student updatedStudent = studentRepository.save(existing);
        return convertToDto(updatedStudent);
    }

    // --- DELETE ---
    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    /*
    // --- GET BY ID ---
    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        return convertToDto(student);
    }
    */
    // --- LIST ALL ---
    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }



    @CircuitBreaker(
            name = "studentServiceCB",
            fallbackMethod = "paginationStudentsFallback"
    )
    @Override
    public Page<Student> paginationStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public Page<Student> paginationStudentsFallback(Pageable pageable, Throwable t) {
        return Page.empty(pageable);
    }
    public StudentDto getStudentByIdFallback(Long id, Throwable t) {
        StudentDto fallbackDto = new StudentDto();
        fallbackDto.setId(id);
        fallbackDto.setFirstName("N/A");
        fallbackDto.setLastName("Service Unavailable");
        fallbackDto.setEmail("fallback@studentservice.local");
        fallbackDto.setIsActive(false);
        return fallbackDto;
    }


    // --- Helper methods ---
    private Student convertToEntity(StudentDto dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setStudentNumber(dto.getStudentNumber());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setGender(dto.getGender());
        student.setDepartmentId(dto.getDepartmentId());
        student.setClassNo(dto.getClassNo());
        student.setEnrollmentDate(dto.getEnrollmentDate());
        student.setIsActive(dto.getIsActive());
        return student;
    }

    @Override
    @CircuitBreaker(
            name = "studentServiceCB",
            fallbackMethod = "getStudentByIdFallback"
    )
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        return convertToDto(student);
    }

    @Override
    public List<Long> getAllStudentIds() {
        return studentRepository.findAllIds();
    }


    private StudentDto convertToDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setStudentNumber(student.getStudentNumber());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setPhone(student.getPhone());
        dto.setDateOfBirth(student.getDateOfBirth());
        dto.setGender(student.getGender());
        dto.setDepartmentId(student.getDepartmentId());
        dto.setClassNo(student.getClassNo());
        dto.setEnrollmentDate(student.getEnrollmentDate());
        dto.setIsActive(student.getIsActive());
        return dto;
    }
}