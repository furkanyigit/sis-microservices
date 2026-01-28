package com.furkan.payment_service.service;

import com.furkan.payment_service.dto.PaymentCreateDto;
import com.furkan.payment_service.dto.PaymentResponseDto;

import java.util.List;

public interface PaymentService {


    PaymentResponseDto createPayment(PaymentCreateDto dto);
    List<PaymentResponseDto> getAllPayments();
    PaymentResponseDto getPaymentById(Long id);
    PaymentResponseDto updatePaymentStatus(Long id, String status);
}