package com.dhruvil.linkedin.post_service.auth;

import com.dhruvil.linkedin.post_service.config.UserContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        Long userId = UserContextHolder.getCurrentUserId();
        if(userId!=null){
            requestTemplate.header("X-USER-ID", userId.toString());
        }
    }
}
