package com.furkan.notificationservice.service.impl;

import com.furkan.notificationservice.client.StudentClient;
import com.furkan.notificationservice.dto.BroadcastNotificationDto;
import com.furkan.notificationservice.dto.StudentTargetDto;
import com.furkan.notificationservice.entity.Notification;
import com.furkan.notificationservice.repository.NotificationRepository;
import com.furkan.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final StudentClient studentClient;

    public void sendBroadcast(BroadcastNotificationDto dto) {

        // 🎯 TÜM ÖĞRENCİLER BURADAN GELİR
        List<Long> studentIds = studentClient.getAllStudentIds();

        studentIds.forEach(studentId -> {

            Notification notification = Notification.builder()
                    .studentId(studentId)
                    .title(dto.title())
                    .message(dto.message())
                    .read(false)
                    .createdAt(LocalDateTime.now())
                    .build();

            notificationRepository.save(notification);
        });
    }

    @Override
    public List<Notification> getNotificationsByStudent(Long studentId) {
        return notificationRepository.findByStudentId(studentId);
    }

    @Override
    public void markAsRead(String notificationId) {
        Notification notification =
                notificationRepository.findById(notificationId)
                        .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);
        notificationRepository.save(notification);
    }
}
