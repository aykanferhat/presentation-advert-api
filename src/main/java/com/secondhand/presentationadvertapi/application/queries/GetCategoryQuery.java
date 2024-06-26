package com.secondhand.presentationadvertapi.application.queries;

import com.secondhand.presentationadvertapi.application.bus.Query;
import com.secondhand.presentationadvertapi.application.queries.model.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetCategoryQuery implements Query<CategoryDTO> {

    private final Long id;
}
