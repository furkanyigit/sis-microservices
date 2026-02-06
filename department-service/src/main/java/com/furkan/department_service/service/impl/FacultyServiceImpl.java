package com.furkan.department_service.service.impl;

import com.furkan.department_service.dto.FacultyDto;
import com.furkan.department_service.entity.Faculty;
import com.furkan.department_service.repository.FacultyRepository;
import com.furkan.department_service.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    @Override
    public FacultyDto createFaculty(FacultyDto dto) {
        Faculty faculty = Faculty.builder()
                .name(dto.getName())
                .build();

        Faculty saved = facultyRepository.save(faculty);

        return mapToDto(saved);
    }

    @Override
    public FacultyDto updateFaculty(Long id, FacultyDto dto) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        faculty.setName(dto.getName());

        return mapToDto(facultyRepository.save(faculty));
    }

    @Override
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public FacultyDto getFacultyById(Long id) {
        return facultyRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
    }

    @Override
    public List<FacultyDto> getAllFaculties() {
        return facultyRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    private FacultyDto mapToDto(Faculty faculty) {
        return FacultyDto.builder()
                .id(faculty.getId())
                .name(faculty.getName())
                .build();
    }
}
