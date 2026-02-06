package com.furkan.studentservice.client;

import com.furkan.studentservice.dto.ExamResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

// name = "exam-service" -> Eureka'da kayıtlı olan isimle AYNI olmalı
@FeignClient(name = "exam-service", path = "/exams")
public interface ExamServiceClient {

    @GetMapping("/student/{studentId}")
    List<ExamResultDTO> getExamsByStudentId(@PathVariable("studentId") Long studentId);
}