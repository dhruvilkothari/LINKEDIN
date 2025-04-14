package com.dhruvil.linkedin.user_service.client;

import com.dhruvil.linkedin.user_service.event.Notification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "notification-service", path = "/notifications")
public interface NotificationClient {
    @GetMapping("/")
    List<Notification> getAllMyNotification(@RequestHeader("X-User-Id") Long userId);

}
