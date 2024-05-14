package com.secondhand.presentationadvertapi.application.queries.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private Long version;
    private String createdBy;
    private Instant creationDate;
    private String modifiedBy;
    private Instant lastModifiedDate;
}
