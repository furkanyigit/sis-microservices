package com.furkan.studentservice.controller;

import com.furkan.studentservice.dto.StudentDto;
import com.furkan.studentservice.entity.Student;
import com.furkan.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // --- CREATE ---
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto created = studentService.createStudent(studentDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // --- UPDATE ---
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentDto studentDto
    ) {
        StudentDto updated = studentService.updateStudent(id, studentDto);
        return ResponseEntity.ok(updated);
    }

    // --- DELETE ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // --- GET BY ID ---
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        StudentDto dto = studentService.getStudentById(id);
        return ResponseEntity.ok(dto);
    }

    // --- LIST ALL ---
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/students/ids")
    public List<Long> getAllStudentIds() {
        return studentService.getAllStudentIds();
    }

    @GetMapping("/{id}/with-grades")
    public ResponseEntity<Map<String, Object>> getStudentWithGrades(@PathVariable Long id) {

        Map<String, Object> result = studentService.getStudentWithExamResults(id);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagination")
    public Page<Student> paginationStudents(
            @RequestParam int page,
            @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return studentService.paginationStudents(pageable);
    }
}