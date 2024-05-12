package com.secondhand.presentationadvertapi.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCategoryRequest {

    @NotBlank(message = "validation.category.request.create.name.blank")
    @Size(max = 30, message = "validation.category.request.create.name.max.length")
    private String name;
}
