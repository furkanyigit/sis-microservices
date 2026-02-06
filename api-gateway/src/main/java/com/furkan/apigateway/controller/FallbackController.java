package com.furkan.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    // Öğrenci Servisi Sigortası Atarsa Burası Çalışır
    @GetMapping("/student")
    public Mono<String> studentFallback() {
        return Mono.just("⚠️ Öğrenci Servisi şu an çok yoğun veya cevap veremiyor. (Gateway Circuit Breaker Devrede)");
    }

    // Kurs Servisi Sigortası Atarsa Burası Çalışır
    @GetMapping("/course")
    public Mono<String> courseFallback() {
        return Mono.just("⚠️ Kurs Servisi şu an ulaşılamaz durumda.");
    }

    // Kayıt (Enrollment) Servisi Fallback
    @GetMapping("/enrollment")
    public Mono<String> enrollmentFallback() {
        return Mono.just("⚠️ Kayıt işlemleri şu an yapılamıyor. Lütfen daha sonra deneyin.");
    }

    // Bildirim (Notification) Servisi Fallback
    @GetMapping("/notification")
    public Mono<String> notificationFallback() {
        return Mono.just("⚠️ Bildirim servisi meşgul.");
    }

    // Ödeme (Payment) Servisi Fallback
    @GetMapping("/payment")
    public Mono<String> paymentFallback() {
        return Mono.just("⚠️ Ödeme sistemi şu an yanıt vermiyor.");
    }

    // Analiz (Analytics) Servisi Fallback
    @GetMapping("/analytics")
    public Mono<String> analyticsFallback() {
        return Mono.just("⚠️ Analiz verilerine şu an ulaşılamıyor.");
    }
}