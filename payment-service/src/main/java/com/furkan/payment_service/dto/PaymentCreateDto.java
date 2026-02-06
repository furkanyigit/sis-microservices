package com.furkan.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCreateDto {
    private Long studentId;    // Ödemeyi yapan öğrenci ID’si
    private Double amount;     // Ödeme miktarı
    private String method;     // Ödeme yöntemi (CREDIT_CARD, EFT, PAYPAL...)
}