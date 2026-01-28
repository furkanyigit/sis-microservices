package com.furkan.department_service.service;

import com.furkan.department_service.dto.FacultyDto;

import java.util.List;

public interface FacultyService {

    FacultyDto createFaculty(FacultyDto facultyDto);
    FacultyDto updateFaculty(Long id, FacultyDto facultyDto);
    void deleteFaculty(Long id);
    FacultyDto getFacultyById(Long id);
    List<FacultyDto> getAllFaculties();
}
