package com.secondhand.presentationadvertapi.application.commands.handlers;

import com.secondhand.presentationadvertapi.application.bus.CommandHandler;
import com.secondhand.presentationadvertapi.application.commands.CreateCategoryCommand;
import com.secondhand.presentationadvertapi.application.repository.CategoryRepository;
import com.secondhand.presentationadvertapi.domain.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateCategoryCommandHandler extends CommandHandler<CreateCategoryCommand> {
    private final CategoryRepository categoryRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAdvertCommandHandler.class);

    public CreateCategoryCommandHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected void execute(CreateCategoryCommand command) {
        Category category = Category.create(command.getId(), command.getName());
        categoryRepository.save(category);
        LOGGER.info("Category created, id: {}", command.getId());
    }
}
