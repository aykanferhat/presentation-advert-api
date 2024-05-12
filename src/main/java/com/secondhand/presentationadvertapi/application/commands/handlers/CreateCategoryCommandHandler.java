package com.secondhand.presentationadvertapi.application.commands.handlers;

import com.secondhand.presentationadvertapi.application.bus.CommandHandler;
import com.secondhand.presentationadvertapi.application.commands.CreateAdvertCommand;
import com.secondhand.presentationadvertapi.application.commands.CreateCategoryCommand;
import com.secondhand.presentationadvertapi.application.repository.AdvertRepository;
import com.secondhand.presentationadvertapi.application.repository.CategoryRepository;
import com.secondhand.presentationadvertapi.domain.Advert;
import com.secondhand.presentationadvertapi.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CreateCategoryCommandHandler extends CommandHandler<CreateCategoryCommand> {
    private final CategoryRepository categoryRepository;

    public CreateCategoryCommandHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected void execute(CreateCategoryCommand command) {
        Category category = Category.create(command.getId(), command.getName());
        categoryRepository.save(category);
    }
}
