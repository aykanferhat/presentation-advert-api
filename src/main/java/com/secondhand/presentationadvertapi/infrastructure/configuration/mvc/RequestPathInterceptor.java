
package com.secondhand.presentationadvertapi.infrastructure.configuration.mvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

public class RequestPathInterceptor implements HandlerInterceptor {
    public RequestPathInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put("Request-Path", this.buildRequestPath(request));
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove("Request-Path");
    }

    private String buildRequestPath(HttpServletRequest request) {
        return request.getMethod() + " " + request.getRequestURI() + (StringUtils.isNotBlank(request.getQueryString()) ? "?" + request.getQueryString() : "");
    }
}
