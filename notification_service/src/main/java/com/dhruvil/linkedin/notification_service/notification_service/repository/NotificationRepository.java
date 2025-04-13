package com.dhruvil.linkedin.notification_service.notification_service.repository;

import com.dhruvil.linkedin.notification_service.notification_service.entity.Notification;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Notification findByUserId(Long userId);
}