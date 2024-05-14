package com.secondhand.presentationadvertapi.infrastructure.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateAdvertRequest {

    @NotBlank(message = "validation.advert.request.create.title.blank")
    @Size(max = 50, message = "validation.advert.request.create.title.max.length")
    private String title;

    @NotBlank(message = "validation.advert.request.create.description.blank")
    private String description;

    @NotNull(message = "validation.advert.request.create.categoryId.not.null")
    private Long categoryId;
}
