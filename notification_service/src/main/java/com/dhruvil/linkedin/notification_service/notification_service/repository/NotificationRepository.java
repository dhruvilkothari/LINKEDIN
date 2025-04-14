package com.dhruvil.linkedin.notification_service.notification_service.repository;

import com.dhruvil.linkedin.notification_service.notification_service.entity.Notification;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Configuration
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);

}