package com.furkan.notificationservice.controller;

import com.furkan.notificationservice.dto.BroadcastNotificationDto;
import com.furkan.notificationservice.entity.Notification;
import com.furkan.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    // 🔔 Admin → tüm öğrencilere gönderir
    @PostMapping("/broadcast")
    public void sendBroadcast(@RequestBody BroadcastNotificationDto dto) {
        notificationService.sendBroadcast(dto);
    }

    // 👨‍🎓 Öğrenci → kendi bildirimlerini görür
    @GetMapping("/student/{studentId}")
    public List<Notification> getStudentNotifications(
            @PathVariable Long studentId) {
        return notificationService.getNotificationsByStudent(studentId);
    }

    // ✔ Okundu işaretle
    @PutMapping("/{id}/read")
    public void markAsRead(@PathVariable String id) {
        notificationService.markAsRead(id);
    }
}
