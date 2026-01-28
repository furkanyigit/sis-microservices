package com.furkan.enrollmentservice.client;

import com.furkan.enrollmentservice.dto.NotificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notification-service")
public interface NotificationClient {

    @PostMapping("/notifications/send")
    void sendNotification(NotificationDto notificationDto);
}