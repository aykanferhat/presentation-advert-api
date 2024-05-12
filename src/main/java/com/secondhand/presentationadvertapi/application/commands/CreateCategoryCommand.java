package com.secondhand.presentationadvertapi.application.commands;

import com.secondhand.presentationadvertapi.application.bus.Command;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CreateCategoryCommand implements Command {

    @Transient
    private long id;

    @NotBlank(message = "validation.category.request.create.name.blank")
    @Size(max = 30, message = "validation.category.request.create.name.max.length")
    private String name;
}
