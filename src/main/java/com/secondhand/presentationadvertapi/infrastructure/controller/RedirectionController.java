package com.secondhand.presentationadvertapi.infrastructure.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Hidden
@RestController
@RequestMapping
public class RedirectionController {

    @GetMapping
    public void redirectToSwagger(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.sendRedirect("/swagger-ui/index.html");
    }
}
