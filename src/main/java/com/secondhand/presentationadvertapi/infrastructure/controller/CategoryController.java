package com.secondhand.presentationadvertapi.infrastructure.controller;

import an.awesome.pipelinr.Pipeline;
import com.secondhand.presentationadvertapi.application.commands.CreateCategoryCommand;
import com.secondhand.presentationadvertapi.application.queries.GetCategoryQuery;
import com.secondhand.presentationadvertapi.application.service.IdGenerationService;
import com.secondhand.presentationadvertapi.infrastructure.controller.mapper.CategoryResponseMapper;
import com.secondhand.presentationadvertapi.infrastructure.controller.model.CategoryResponse;
import com.secondhand.presentationadvertapi.infrastructure.controller.model.CreateCategoryRequest;
import com.secondhand.presentationadvertapi.infrastructure.controller.util.ResponseUtils;
import com.secondhand.presentationadvertapi.domain.Category;
import jakarta.validation.Valid;
import org.hibernate.StaleObjectStateException;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final Pipeline pipeline;

    private final IdGenerationService idGenerationService;

    public CategoryController(Pipeline pipeline, IdGenerationService idGenerationService) {
        this.pipeline = pipeline;
        this.idGenerationService = idGenerationService;
    }

    @PostMapping
    @Transactional
    @Retryable(retryFor = {ConcurrencyFailureException.class, StaleObjectStateException.class}, backoff = @Backoff(delay = 100L, multiplier = 3), maxAttempts = 3)
    public ResponseEntity<ResponseUtils.CreatedResponse> create(
            @RequestHeader(name = "X-UserEmail") String userEmail,
            @RequestBody @Valid CreateCategoryRequest request
    ) {
        long categoryId = idGenerationService.generateCategoryId();
        pipeline.send(new CreateCategoryCommand(categoryId, request.getName()));
        return ResponseUtils.createdResponse(getClass(), categoryId);
    }

    @GetMapping("/{id}")
    @Retryable(retryFor = {ConcurrencyFailureException.class, StaleObjectStateException.class}, backoff = @Backoff(delay = 100L, multiplier = 3), maxAttempts = 3)
    public ResponseEntity<CategoryResponse> get(@PathVariable Long id) {
        Category category = pipeline.send(new GetCategoryQuery(id));
        CategoryResponse categoryResponse = CategoryResponseMapper.map(category);
        return ResponseEntity.ok(categoryResponse);
    }
}
