package com.dhruvil.linkedin.notification_service.posts_service.event;

import lombok.Data;

@Data
public class PostCreatedEvent {
    Long creatorId;
    String content;
    Long postId;
}