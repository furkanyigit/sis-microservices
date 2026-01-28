package com.furkan.courseservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {

    private Long id;
    private String code;  // unique student ID (e.g., 2025001)
    private String name;
    private String credit;
    private String departmentId;
    private String classNo;
}
