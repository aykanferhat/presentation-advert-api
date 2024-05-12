package com.secondhand.presentationadvertapi.controller.mapper;

import com.secondhand.presentationadvertapi.controller.model.AdvertResponse;
import com.secondhand.presentationadvertapi.controller.model.CategoryResponse;
import com.secondhand.presentationadvertapi.domain.Advert;
import com.secondhand.presentationadvertapi.domain.Category;

public class CategoryResponseMapper {

    public static CategoryResponse map(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
//        return new AdvertResponse(
//                advert.getId(),
//                advert.getTitle(),
//                advert.getDescription(),
//                new AdvertResponse.Category(advert.getCategory().getId(), advert.getCategory().getName())
//        );
    }
}
