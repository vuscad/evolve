package com.objective.evolve.handler;

import com.objective.evolve.model.Area;
import com.objective.evolve.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class AreaHandler {

    private final AreaRepository repository;

    @Autowired
    public AreaHandler(AreaRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(repository.findAll(), Area.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return repository.findById(request.pathVariable("id"))
                .flatMap(area -> ServerResponse.ok().body(Mono.just(area), Area.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(repository.insert(request.bodyToMono(Area.class)), Area.class);
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest){

        return repository.findById(serverRequest.pathVariable("id"))
                .flatMap(user -> ServerResponse.ok()
                        .body(repository.save(serverRequest.bodyToMono(Area.class).block()), Area.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        return repository.findById(serverRequest.pathVariable("id"))
                .flatMap(user -> ServerResponse
                        .noContent()
                        .build(repository.delete(user)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
