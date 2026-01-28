package com.furkan.notificationservice.service;

import com.furkan.notificationservice.dto.BroadcastNotificationDto;
import com.furkan.notificationservice.entity.Notification;

import java.util.List;

public interface NotificationService {

    void sendBroadcast(BroadcastNotificationDto dto);

    List<Notification> getNotificationsByStudent(Long studentId);

    void markAsRead(String notificationId);
}
