package com.furkan.courseservice.controller;

import com.furkan.courseservice.dto.CourseDto;
import com.furkan.courseservice.entity.Course;
import com.furkan.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    /* =========================
       CREATE
       ========================= */
    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        CourseDto createdCourse = courseService.createCourse(courseDto);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    /* =========================
       UPDATE
       ========================= */
    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseDto courseDto) {

        CourseDto updatedCourse = courseService.updateCourse(id, courseDto);
        return ResponseEntity.ok(updatedCourse);
    }

    /* =========================
       DELETE
       ========================= */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    /* =========================
       GET BY ID
       ========================= */
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        CourseDto courseDto = courseService.getCourseById(id);
        return ResponseEntity.ok(courseDto);
    }

    /* =========================
       GET ALL
       ========================= */
    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    /* =========================
       PAGINATION
       ========================= */
    @GetMapping("/page")
    public ResponseEntity<Page<Course>> getCoursesWithPagination(
            @PageableDefault(size = 10) Pageable pageable) {

        Page<Course> coursePage = courseService.paginationCourses(pageable);
        return ResponseEntity.ok(coursePage);
    }
}
