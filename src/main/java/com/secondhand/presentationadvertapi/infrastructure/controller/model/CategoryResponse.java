package com.secondhand.presentationadvertapi.infrastructure.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private Long version;
}
