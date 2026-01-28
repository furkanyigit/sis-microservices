package com.furkan.notificationservice.dto;

public record NotificationRequestDto(
        Long studentId,
        String email,
        String message,
        String channel
) {}
