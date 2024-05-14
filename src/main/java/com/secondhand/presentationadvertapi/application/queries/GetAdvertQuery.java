package com.secondhand.presentationadvertapi.application.queries;

import com.secondhand.presentationadvertapi.application.bus.Query;
import com.secondhand.presentationadvertapi.application.queries.model.AdvertDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAdvertQuery implements Query<AdvertDTO> {

    private final Long id;
}
