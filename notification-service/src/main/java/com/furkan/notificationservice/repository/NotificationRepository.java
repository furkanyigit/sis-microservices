package com.furkan.notificationservice.repository;

import com.furkan.notificationservice.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {

    // Custom query methods (isteğe bağlı)

    List<Notification> findByStudentId(Long studentId);

    List<Notification> findByStatus(String status);

    List<Notification> findByChannel(String channel);
}
