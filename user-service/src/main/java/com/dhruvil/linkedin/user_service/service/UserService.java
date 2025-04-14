package com.dhruvil.linkedin.user_service.service;

import com.dhruvil.linkedin.user_service.auth.UserContextHolder;
import com.dhruvil.linkedin.user_service.client.NotificationClient;
import com.dhruvil.linkedin.user_service.event.Notification;
import com.dhruvil.linkedin.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final NotificationClient notificationClient;

    public List<Notification> getAllMyNotification(){
        Long userId = UserContextHolder.getCurrentUserId();
        return notificationClient.getAllMyNotification(userId);
    }
}
