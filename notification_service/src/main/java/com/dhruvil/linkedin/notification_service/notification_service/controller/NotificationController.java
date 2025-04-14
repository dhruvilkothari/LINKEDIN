package com.dhruvil.linkedin.notification_service.notification_service.controller;

import com.dhruvil.linkedin.notification_service.notification_service.auth.UserContextHolder;
import com.dhruvil.linkedin.notification_service.notification_service.entity.Notification;
import com.dhruvil.linkedin.notification_service.service.SendNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class NotificationController {
    private final SendNotification notificationService;

    @GetMapping("/")
    public ResponseEntity<List<Notification>> getNotification() {
        long userId = UserContextHolder.getCurrentUserId();
        return notificationService.getNotifications(userId);
    }
}
