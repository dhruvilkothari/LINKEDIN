package com.dhruvil.linkedin.notification_service.service;

import com.dhruvil.linkedin.notification_service.notification_service.entity.Notification;
import com.dhruvil.linkedin.notification_service.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendNotification {

    private final NotificationRepository notificationRepository;

    public void send(Long userId, String message) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setUserId(userId);

        notificationRepository.save(notification);
        log.info("Notification saved for user: {}", userId);
    }
    public ResponseEntity<List<Notification>> getNotifications(Long userId) {
        return ResponseEntity.ok(notificationRepository.findByUserId(userId));
    }
}