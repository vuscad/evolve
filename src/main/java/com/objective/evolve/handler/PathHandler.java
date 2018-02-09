package com.objective.evolve.handler;

import com.objective.evolve.model.Path;
import com.objective.evolve.repository.PathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class PathHandler {

    private final PathRepository repository;

    @Autowired
    public PathHandler(PathRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(repository.findAll(), Path.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return repository.findById(request.pathVariable("id"))
                .flatMap(path -> ServerResponse.ok().body(Mono.just(path), Path.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(repository.insert(request.bodyToMono(Path.class)), Path.class);
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest){

        return repository.findById(serverRequest.pathVariable("id"))
                .flatMap(user -> ServerResponse.ok()
                        .body(repository.save(serverRequest.bodyToMono(Path.class).block()), Path.class))
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
