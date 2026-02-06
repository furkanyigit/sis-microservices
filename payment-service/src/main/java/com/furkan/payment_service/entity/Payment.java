package com.furkan.payment_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private Double amount;
    private String status; // PENDING, SUCCESS, FAILED
    private String method; // CREDIT_CARD, EFT, PAYPAL...
    private LocalDateTime createdAt;
}