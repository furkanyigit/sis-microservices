package com.furkan.department_service.controller;

import com.furkan.department_service.dto.FacultyDto;
import com.furkan.department_service.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculties")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    // CREATE
    @PostMapping
    public ResponseEntity<FacultyDto> createFaculty(
            @RequestBody FacultyDto facultyDto) {

        FacultyDto created = facultyService.createFaculty(facultyDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<FacultyDto> updateFaculty(
            @PathVariable Long id,
            @RequestBody FacultyDto facultyDto) {

        FacultyDto updated = facultyService.updateFaculty(id, facultyDto);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<FacultyDto> getFacultyById(@PathVariable Long id) {
        FacultyDto faculty = facultyService.getFacultyById(id);
        return ResponseEntity.ok(faculty);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<FacultyDto>> getAllFaculties() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }
}
