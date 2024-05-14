package com.secondhand.presentationadvertapi.infrastructure.handlers;

import com.secondhand.presentationadvertapi.application.bus.QueryHandler;
import com.secondhand.presentationadvertapi.application.queries.GetCategoryQuery;
import com.secondhand.presentationadvertapi.application.repository.CategoryRepository;
import com.secondhand.presentationadvertapi.domain.Category;
import com.secondhand.presentationadvertapi.domain.exceptions.NotFoundException;
import com.secondhand.presentationadvertapi.application.queries.model.CategoryDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetCategoryQueryHandler extends QueryHandler<GetCategoryQuery, CategoryDTO> {

    private final CategoryRepository categoryRepository;

    public GetCategoryQueryHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected CategoryDTO execute(GetCategoryQuery query) {
        Optional<Category> optionalCategory = categoryRepository.findById(query.getId());
        if (optionalCategory.isEmpty()) {
            throw new NotFoundException("validation.category.not.found", query.getId());
        }
        return map(optionalCategory.get());
    }

    private CategoryDTO map(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getVersion(),
                category.getAudit().getCreatedBy(),
                category.getAudit().getCreationDate(),
                category.getAudit().getModifiedBy(),
                category.getAudit().getLastModifiedDate()
        );
    }
}
