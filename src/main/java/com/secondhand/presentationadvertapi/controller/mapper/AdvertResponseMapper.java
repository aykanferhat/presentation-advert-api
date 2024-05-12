package com.secondhand.presentationadvertapi.controller.mapper;

import com.secondhand.presentationadvertapi.controller.model.AdvertResponse;
import com.secondhand.presentationadvertapi.domain.Advert;

public class AdvertResponseMapper {

    public static AdvertResponse map(Advert advert) {
        return new AdvertResponse(
                advert.getId(),
                advert.getTitle(),
                advert.getDescription(),
                advert.getCategory().getId()
        );
//        return new AdvertResponse(
//                advert.getId(),
//                advert.getTitle(),
//                advert.getDescription(),
//                new AdvertResponse.Category(advert.getCategory().getId(), advert.getCategory().getName())
//        );
    }
}
