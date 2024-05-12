package com.secondhand.presentationadvertapi.application.commands;

import com.secondhand.presentationadvertapi.application.bus.Command;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CreateAdvertCommand implements Command {

    @Transient
    private long advertId;

    @NotBlank(message = "validation.advert.request.create.title.blank")
    @Size(max = 50, message = "validation.advert.request.create.title.max.length")
    private String title;

    @NotBlank(message = "validation.advert.request.create.description.blank")
    private String description;

    @NotNull(message = "validation.advert.request.create.categoryId.not.null")
    private Long categoryId;
}

