package com.furkan.payment_service.service.impl;

import com.furkan.payment_service.dto.PaymentCreateDto;
import com.furkan.payment_service.dto.PaymentResponseDto;
import com.furkan.payment_service.entity.Payment;
import com.furkan.payment_service.repository.PaymentRepository;
import com.furkan.payment_service.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentResponseDto createPayment(PaymentCreateDto dto) {
        Payment payment = Payment.builder()
                .studentId(dto.getStudentId())
                .amount(dto.getAmount())
                .method(dto.getMethod())
                .status("PENDING") // default status
                .createdAt(LocalDateTime.now())
                .build();

        Payment saved = paymentRepository.save(payment);
        return mapToResponseDto(saved);
    }

    @Override
    public List<PaymentResponseDto> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponseDto getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        return mapToResponseDto(payment);
    }

    @Override
    public PaymentResponseDto updatePaymentStatus(Long id, String status) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        payment.setStatus(status);
        Payment updated = paymentRepository.save(payment);
        return mapToResponseDto(updated);
    }

    // Helper method: Entity → DTO dönüşümü
    private PaymentResponseDto mapToResponseDto(Payment payment) {
        return new PaymentResponseDto(
                payment.getId(),
                payment.getStudentId(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getMethod(),
                payment.getCreatedAt()
        );
    }
}