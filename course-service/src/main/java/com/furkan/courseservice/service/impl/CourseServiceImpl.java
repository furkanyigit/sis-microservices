package com.furkan.courseservice.service.impl;

import com.furkan.courseservice.dto.CourseDto;
import com.furkan.courseservice.entity.Course;
import com.furkan.courseservice.repository.CourseRepository;
import com.furkan.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Course course = mapToEntity(courseDto);
        Course savedCourse = courseRepository.save(course);
        return mapToDto(savedCourse);
    }

    @Override
    public CourseDto updateCourse(Long id, CourseDto courseDto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        course.setCode(courseDto.getCode());
        course.setName(courseDto.getName());
        course.setCredit(courseDto.getCredit());
        course.setDepartmentId(courseDto.getDepartmentId());
        course.setClassNo(courseDto.getClassNo());

        Course updatedCourse = courseRepository.save(course);
        return mapToDto(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        courseRepository.delete(course);
    }

    @Override
    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        return mapToDto(course);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Course> paginationCourses(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    /* =========================
       Mapping methods
       ========================= */

    private Course mapToEntity(CourseDto dto) {
        Course course = new Course();
        course.setCode(dto.getCode());
        course.setName(dto.getName());
        course.setCredit(dto.getCredit());
        course.setDepartmentId(dto.getDepartmentId());
        course.setClassNo(dto.getClassNo());
        return course;
    }

    private CourseDto mapToDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setCode(course.getCode());
        dto.setName(course.getName());
        dto.setCredit(course.getCredit());
        dto.setDepartmentId(course.getDepartmentId());
        dto.setClassNo(course.getClassNo());
        return dto;
    }
}
