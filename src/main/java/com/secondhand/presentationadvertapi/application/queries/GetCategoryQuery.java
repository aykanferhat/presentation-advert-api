package com.secondhand.presentationadvertapi.application.queries;

import com.secondhand.presentationadvertapi.application.bus.Query;
import com.secondhand.presentationadvertapi.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetCategoryQuery implements Query<Category> {

    private final Long id;
}
