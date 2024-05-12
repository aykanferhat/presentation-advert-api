package com.secondhand.presentationadvertapi.application.queries.handlers;

import com.secondhand.presentationadvertapi.application.bus.QueryHandler;
import com.secondhand.presentationadvertapi.application.queries.GetCategoryQuery;
import com.secondhand.presentationadvertapi.application.repository.CategoryRepository;
import com.secondhand.presentationadvertapi.domain.Category;
import com.secondhand.presentationadvertapi.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetCategoryQueryHandler extends QueryHandler<GetCategoryQuery, Category> {

    private final CategoryRepository categoryRepository;

    public GetCategoryQueryHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected Category execute(GetCategoryQuery query) {
        Optional<Category> optionalCategory = categoryRepository.findById(query.getId());
        if (optionalCategory.isEmpty()) {
            throw new NotFoundException("validation.category.not.found", query.getId());
        }
        return optionalCategory.get();
    }
}
