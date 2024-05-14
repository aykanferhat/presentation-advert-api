package com.secondhand.presentationadvertapi.infrastructure.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateAdvertTitleRequest {

    @NotBlank(message = "validation.advert.request.create.title.blank")
    @Size(max = 50, message = "validation.advert.request.create.title.max.length")
    private String title;

}
