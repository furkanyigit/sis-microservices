package com.furkan.studentservice.mapper;

import com.furkan.studentservice.dto.StudentDto;
import com.furkan.studentservice.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

// componentModel = "spring" sayesinde @Autowired ile çağırabilirsin
@Mapper(componentModel = "spring")
public interface StudentMapper {

    // Entity'den DTO'ya çevirir (Senin convertToDto yerine geçer)
    StudentDto toDto(Student student);

    // DTO'dan Entity'e çevirir (Senin convertToEntity yerine geçer)
    Student toEntity(StudentDto dto);

    // İŞTE SİHİR BURADA: Mevcut bir entity'i DTO ile günceller
    // O uzun set set set kodlarını MapStruct burada otomatik üretir.
    void updateStudentFromDto(StudentDto dto, @MappingTarget Student student);
}