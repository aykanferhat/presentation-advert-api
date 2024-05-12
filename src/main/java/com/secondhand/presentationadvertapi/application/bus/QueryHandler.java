package com.secondhand.presentationadvertapi.application.bus;

import an.awesome.pipelinr.Command;

public abstract class QueryHandler<Q extends Query<R>, R> implements Command.Handler<Q, R> {

    @Override
    public final R handle(Q query) {
        return execute(query);
    }

    protected abstract R execute(Q query);
}


