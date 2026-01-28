package com.furkan.notificationservice.dto;

import java.util.List;

public record BroadcastNotificationDto(
        String title,
        String message,
        String channel,
        List<StudentTargetDto> students
) {}
