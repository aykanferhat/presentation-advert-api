package com.secondhand.presentationadvertapi.controller;

import an.awesome.pipelinr.Pipeline;
import com.secondhand.presentationadvertapi.application.commands.CreateAdvertCommand;
import com.secondhand.presentationadvertapi.application.queries.GetAdvertQuery;
import com.secondhand.presentationadvertapi.application.service.IdGenerationService;
import com.secondhand.presentationadvertapi.controller.mapper.AdvertResponseMapper;
import com.secondhand.presentationadvertapi.controller.model.AdvertResponse;
import com.secondhand.presentationadvertapi.controller.model.CreateAdvertRequest;
import com.secondhand.presentationadvertapi.controller.util.ResponseUtils;
import com.secondhand.presentationadvertapi.domain.Advert;
import jakarta.validation.Valid;
import org.hibernate.StaleObjectStateException;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adverts")
public class AdvertController {

    private final Pipeline pipeline;

    private final IdGenerationService idGenerationService;

    public AdvertController(Pipeline pipeline, IdGenerationService idGenerationService) {
        this.pipeline = pipeline;
        this.idGenerationService = idGenerationService;
    }

    @PostMapping
    @Transactional
    @Retryable(retryFor = {ConcurrencyFailureException.class, StaleObjectStateException.class}, backoff = @Backoff(delay = 100L, multiplier = 3), maxAttempts = 3)
    public ResponseEntity<ResponseUtils.CreatedResponse> create(
            @RequestHeader(name = "X-UserEmail") String userEmail,
            @RequestBody @Valid CreateAdvertRequest request
    ) {
        long advertId = idGenerationService.generateAdvertId();
        pipeline.send(new CreateAdvertCommand(advertId, request.getTitle(), request.getDescription(), request.getCategoryId()));
        return ResponseUtils.createdResponse(getClass(), advertId);
    }

    @GetMapping("/{id}")
    @Transactional
    @Retryable(retryFor = {ConcurrencyFailureException.class, StaleObjectStateException.class}, backoff = @Backoff(delay = 100L, multiplier = 3), maxAttempts = 3)
    public ResponseEntity<AdvertResponse> get(@PathVariable Long id) {
        Advert advert = pipeline.send(new GetAdvertQuery(id));
        AdvertResponse advertResponse = AdvertResponseMapper.map(advert);
        return ResponseEntity.ok(advertResponse);
    }
}
