package com.secondhand.presentationadvertapi.infrastructure.controller.mapper;

import com.secondhand.presentationadvertapi.infrastructure.controller.model.CategoryResponse;
import com.secondhand.presentationadvertapi.domain.Category;

public class CategoryResponseMapper {

    public static CategoryResponse map(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getVersion()
        );
//        return new AdvertResponse(
//                advert.getId(),
//                advert.getTitle(),
//                advert.getDescription(),
//                new AdvertResponse.Category(advert.getCategory().getId(), advert.getCategory().getName())
//        );
    }
}
