package com.secondhand.presentationadvertapi.infrastructure.configuration.mvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserInfoInterceptor implements HandlerInterceptor {
    public UserInfoInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userAgent = request.getHeader("User-Agent");
        if (StringUtils.isNotBlank(userAgent)) {
            MDC.put("User-Agent", userAgent);
        }

        String userEmail = request.getHeader("X-UserEmail");
        if (StringUtils.isNotBlank(userEmail)) {
            MDC.put("X-UserEmail", userEmail);
        }

        String userAppAgent = request.getHeader("X-AgentName");
        if (StringUtils.isNotBlank(userAppAgent)) {
            MDC.put("X-UserAgentName", userAppAgent);
        }

        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        MDC.remove("X-UserEmail");
        MDC.remove("User-Agent");
        MDC.remove("X-UserAgentName");
    }
}
