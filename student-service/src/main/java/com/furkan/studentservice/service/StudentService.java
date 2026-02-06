package com.furkan.studentservice.service;

import com.furkan.studentservice.dto.StudentDto;
import com.furkan.studentservice.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);
    StudentDto updateStudent(Long id, StudentDto studentDto);
    void deleteStudent(Long id);
    //StudentDto getStudentById(Long id);
    List<StudentDto> getAllStudents();
    Page<Student> paginationStudents(Pageable pageable);
    StudentDto getStudentById(Long id);
    List<Long> getAllStudentIds();
    Map<String, Object> getStudentWithExamResults(Long studentId);

}