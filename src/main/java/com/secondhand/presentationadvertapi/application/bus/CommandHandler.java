package com.secondhand.presentationadvertapi.application.bus;

import an.awesome.pipelinr.Voidy;

public abstract class CommandHandler<C extends Command> implements Command.Handler<C, Voidy> {

    @Override
    public final Voidy handle(C command) {
        execute(command);
        return null;
    }

    protected abstract void execute(C command);
}