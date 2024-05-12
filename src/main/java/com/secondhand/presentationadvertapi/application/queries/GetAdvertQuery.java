package com.secondhand.presentationadvertapi.application.queries;

import com.secondhand.presentationadvertapi.application.bus.Query;
import com.secondhand.presentationadvertapi.domain.Advert;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAdvertQuery implements Query<Advert> {

    private final Long id;
}
