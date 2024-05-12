package com.secondhand.presentationadvertapi.controller.model;

import com.secondhand.presentationadvertapi.common.Clock;
import com.secondhand.presentationadvertapi.common.DateUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponse {

    private String title;
    private Integer status;
    private String detail;
    private String requestUri;
    private String requestMethod;
    private String instant;

    private ErrorResponse() {
    }

    public static ErrorResponse from(HttpStatus httpStatus, HttpServletRequest request, String detail) {
        ErrorResponse errorDTO = new ErrorResponse();
        errorDTO.setTitle(httpStatus.getReasonPhrase());
        errorDTO.setStatus(httpStatus.value());
        errorDTO.setDetail(detail);
        errorDTO.setRequestUri(request.getRequestURI());
        errorDTO.setRequestMethod(request.getMethod());
        errorDTO.setInstant(DateUtils.formatWithISOInstant(Clock.nowUTC()));
        return errorDTO;
    }
}
