package com.secondhand.presentationadvertapi.infrastructure.bus;

import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionalPipeline implements Pipeline {
    private final Pipeline pipeline;

    public TransactionalPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    @Transactional()
    public <R, C extends an.awesome.pipelinr.Command<R>> R send(C command) {
        return pipeline.send(command);
    }

    @Override
    @Transactional()
    public <N extends Notification> void send(N notification) {
        pipeline.send(notification);
    }
}
