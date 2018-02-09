package com.objective.evolve.handler;

import com.objective.evolve.model.SubArea;
import com.objective.evolve.repository.SubAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class SubAreaHandler {

    private final SubAreaRepository repository;

    @Autowired
    public SubAreaHandler(SubAreaRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(repository.findAll(), SubArea.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return repository.findById(request.pathVariable("id"))
                .flatMap(subSubArea -> ServerResponse.ok().body(Mono.just(subSubArea), SubArea.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(repository.insert(request.bodyToMono(SubArea.class)), SubArea.class);
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest){

        return repository.findById(serverRequest.pathVariable("id"))
                .flatMap(user -> ServerResponse.ok()
                        .body(repository.save(serverRequest.bodyToMono(SubArea.class).block()), SubArea.class))
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
