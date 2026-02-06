package com.furkan.department_service.service;

import com.furkan.department_service.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService{

    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto);
    void deleteDepartment(Long id);
    DepartmentDto getDepartmentById(Long id);
    List<DepartmentDto> getAllDepartments();
}
