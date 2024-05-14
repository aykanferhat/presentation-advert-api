package com.secondhand.presentationadvertapi.application.commands.handlers;

import com.secondhand.presentationadvertapi.application.bus.CommandHandler;
import com.secondhand.presentationadvertapi.application.commands.CreateAdvertCommand;
import com.secondhand.presentationadvertapi.application.repository.AdvertRepository;
import com.secondhand.presentationadvertapi.application.repository.CategoryRepository;
import com.secondhand.presentationadvertapi.domain.Advert;
import com.secondhand.presentationadvertapi.domain.Category;
import com.secondhand.presentationadvertapi.domain.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateAdvertCommandHandler extends CommandHandler<CreateAdvertCommand> {
    private final AdvertRepository advertRepository;

    private final CategoryRepository categoryRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAdvertCommandHandler.class);

    public CreateAdvertCommandHandler(AdvertRepository advertRepository, CategoryRepository categoryRepository) {
        this.advertRepository = advertRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected void execute(CreateAdvertCommand command) {
        var optionalCategory = categoryRepository.findById(command.getCategoryId());
        if (optionalCategory.isEmpty()) {
            throw new NotFoundException("validation.advert.category.not.found", command.getCategoryId());
        }
        Category category = optionalCategory.get();
        Advert advert = Advert.create(command.getAdvertId(), command.getTitle(), command.getDescription(), category);
        advertRepository.save(advert);
        LOGGER.info("Advert created, id: {}", command.getAdvertId());
    }
}
