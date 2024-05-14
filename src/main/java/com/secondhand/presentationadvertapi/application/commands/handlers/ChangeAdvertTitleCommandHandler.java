package com.secondhand.presentationadvertapi.application.commands.handlers;

import com.secondhand.presentationadvertapi.application.bus.CommandHandler;
import com.secondhand.presentationadvertapi.application.commands.ChangeAdvertTitleCommand;
import com.secondhand.presentationadvertapi.application.repository.AdvertRepository;
import com.secondhand.presentationadvertapi.domain.Advert;
import com.secondhand.presentationadvertapi.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChangeAdvertTitleCommandHandler extends CommandHandler<ChangeAdvertTitleCommand> {

    private final AdvertRepository advertRepository;

    public ChangeAdvertTitleCommandHandler(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @Override
    protected void execute(ChangeAdvertTitleCommand command) {
        Optional<Advert> optionalAdvert = advertRepository.findById(command.getId());
        if (optionalAdvert.isEmpty()) {
            throw new NotFoundException("validation.advert.not.found", command.getId());
        }
        Advert advert = optionalAdvert.get();
        advert.changeTitle(command.getTitle());
        advertRepository.save(advert);
    }
}
