package com.secondhand.presentationadvertapi.infrastructure.handlers;

import com.secondhand.presentationadvertapi.application.bus.QueryHandler;
import com.secondhand.presentationadvertapi.application.queries.GetAdvertQuery;
import com.secondhand.presentationadvertapi.application.repository.AdvertRepository;
import com.secondhand.presentationadvertapi.domain.Advert;
import com.secondhand.presentationadvertapi.domain.exceptions.NotFoundException;
import com.secondhand.presentationadvertapi.application.queries.model.AdvertDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetAdvertQueryHandler extends QueryHandler<GetAdvertQuery, AdvertDTO> {

    private final AdvertRepository advertRepository;

    public GetAdvertQueryHandler(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @Override
    protected AdvertDTO execute(GetAdvertQuery query) {
        Optional<Advert> optionalAdvert = advertRepository.findById(query.getId());
        if (optionalAdvert.isEmpty()) {
            throw new NotFoundException("validation.advert.not.found", query.getId());
        }
        return map(optionalAdvert.get());
    }

    public static AdvertDTO map(Advert advert) {
        return new AdvertDTO(
                advert.getId(),
                advert.getTitle(),
                advert.getDescription(),
                advert.getCategory().getId(),
                advert.getVersion(),
                advert.getAudit().getCreatedBy(),
                advert.getAudit().getCreationDate(),
                advert.getAudit().getModifiedBy(),
                advert.getAudit().getLastModifiedDate()
        );
    }
}
