package com.furkan.notificationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "notification")
public class Notification {

    @Id
    private String id;   // MongoDB ObjectId stored as String

    private Long studentId;
    private String title;
    private String message;
    private String channel; // EMAIL, SMS, PUSH
    private String status;  // SENT, FAILED, PENDING
    private boolean read = false;
    private LocalDateTime createdAt;
}
