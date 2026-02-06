package com.furkan.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {
    private Long id;            // Ödemenin ID’si
    private Long studentId;     // Ödemeyi yapan öğrenci ID
    private Double amount;      // Ödeme miktarı
    private String status;      // Ödeme durumu (PENDING, SUCCESS, FAILED)
    private String method;      // Ödeme yöntemi
    private LocalDateTime createdAt; // Ödemenin oluşturulma zamanı
}
