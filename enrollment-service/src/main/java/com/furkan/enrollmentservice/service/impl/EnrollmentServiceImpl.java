package com.furkan.enrollmentservice.service.impl;

import com.furkan.enrollmentservice.client.NotificationClient;
import com.furkan.enrollmentservice.client.PaymentClient;
import com.furkan.enrollmentservice.dto.EnrollmentCreateDto;
import com.furkan.enrollmentservice.dto.EnrollmentResponseDto;
import com.furkan.enrollmentservice.dto.NotificationDto;
import com.furkan.enrollmentservice.entity.Enrollment;
import com.furkan.enrollmentservice.repository.EnrollmentRepository;
import com.furkan.enrollmentservice.service.EnrollmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final PaymentClient paymentClient;
    private final NotificationClient notificationClient;

    public EnrollmentServiceImpl(
            EnrollmentRepository enrollmentRepository,
            PaymentClient paymentClient,
            NotificationClient notificationClient
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.paymentClient = paymentClient;
        this.notificationClient = notificationClient;
    }
    @Override
    public EnrollmentResponseDto enrollStudent(EnrollmentCreateDto dto) {

        // 1️⃣ Payment kontrolü
        boolean hasPaid = paymentClient.checkPayment(dto.getStudentId(), dto.getCourse());
        if (!hasPaid) {
            throw new RuntimeException("Ödeme yapılmadan ders kaydı oluşturulamaz!");
        }

        // 2️⃣ Kayıt oluştur
        Enrollment enrollment = Enrollment.builder()
                .studentId(dto.getStudentId())
                .course(dto.getCourse())
                .semester(dto.getSemester())
                .status(dto.getStatus() != null ? dto.getStatus() : "ENROLLED")
                .build();

        Enrollment saved = enrollmentRepository.save(enrollment);

        // 3️⃣ Notification gönder
        NotificationDto notificationDto = new NotificationDto(
                saved.getStudentId(),
                "Ders Kaydı Başarılı",
                "Tebrikler! " + saved.getCourse() + " dersine kaydınız başarıyla tamamlandı."
        );
        notificationClient.sendNotification(notificationDto);

        return mapToResponseDto(saved);
    }
    /*
    @Override
    public EnrollmentResponseDto enrollStudent(EnrollmentCreateDto dto) {
        Enrollment enrollment = Enrollment.builder()
                .studentId(dto.getStudentId())
                .course(dto.getCourse())
                .semester(dto.getSemester())
                .status(dto.getStatus() != null ? dto.getStatus() : "ENROLLED")
                .build();

        Enrollment saved = enrollmentRepository.save(enrollment);
        return mapToResponseDto(saved);
    }
    */
    @Override
    public List<EnrollmentResponseDto> getAllEnrollments() {
        return enrollmentRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentResponseDto> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentResponseDto> getEnrollmentsByCourse(String course) {
        return enrollmentRepository.findByCourse(course)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }



    @Override
    public EnrollmentResponseDto updateEnrollmentStatus(Long id, String status) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + id));
        enrollment.setStatus(status);
        Enrollment updated = enrollmentRepository.save(enrollment);
        return mapToResponseDto(updated);
    }

    private EnrollmentResponseDto mapToResponseDto(Enrollment enrollment) {
        return new EnrollmentResponseDto(
                enrollment.getId(),
                enrollment.getStudentId(),
                enrollment.getCourse(),
                enrollment.getSemester(),
                enrollment.getStatus()
        );
    }
}
