package com.secondhand.presentationadvertapi.application.queries.handlers;

import com.secondhand.presentationadvertapi.application.bus.QueryHandler;
import com.secondhand.presentationadvertapi.application.queries.GetAdvertQuery;
import com.secondhand.presentationadvertapi.application.repository.AdvertRepository;
import com.secondhand.presentationadvertapi.domain.Advert;
import com.secondhand.presentationadvertapi.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetAdvertQueryHandler extends QueryHandler<GetAdvertQuery, Advert> {

    private final AdvertRepository advertRepository;

    public GetAdvertQueryHandler(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @Override
    protected Advert execute(GetAdvertQuery query) {
        Optional<Advert> optionalAdvert = advertRepository.findById(query.getId());
        if (optionalAdvert.isEmpty()) {
            throw new NotFoundException("validation.advert.not.found", query.getId());
        }
        return optionalAdvert.get();
    }
}
