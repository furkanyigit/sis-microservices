package com.furkan.courseservice.service;

import com.furkan.courseservice.dto.CourseDto;
import com.furkan.courseservice.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {

    CourseDto createCourse(CourseDto courseDto);
    CourseDto updateCourse(Long id, CourseDto courseDto);
    void deleteCourse(Long id);
    CourseDto getCourseById(Long id);
    List<CourseDto> getAllCourses();
    Page<Course> paginationCourses(Pageable pageable);

}
