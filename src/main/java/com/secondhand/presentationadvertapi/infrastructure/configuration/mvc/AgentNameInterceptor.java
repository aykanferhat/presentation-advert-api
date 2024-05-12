package com.secondhand.presentationadvertapi.infrastructure.configuration.mvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

public class AgentNameInterceptor implements HandlerInterceptor {
    private final String applicationName;

    public AgentNameInterceptor(String applicationName) {
        this.applicationName = applicationName;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put("X-AgentName", this.applicationName);
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove("X-AgentName");
    }
}
