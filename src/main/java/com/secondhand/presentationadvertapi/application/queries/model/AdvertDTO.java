package com.secondhand.presentationadvertapi.application.queries.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class AdvertDTO {
    private Long id;
    private String title;
    private String description;
    private Long categoryId;
    private Long version;
    private String createdBy;
    private Instant creationDate;
    private String modifiedBy;
    private Instant lastModifiedDate;
}
