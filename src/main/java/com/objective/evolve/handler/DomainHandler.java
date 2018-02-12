package com.objective.evolve.handler;

import com.objective.evolve.model.Domain;
import com.objective.evolve.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class DomainHandler {

    private final DomainRepository repository;

    @Autowired
    public DomainHandler(DomainRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(repository.findAll(), Domain.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return repository.findById(request.pathVariable("id"))
                .flatMap(domain -> ServerResponse.ok().body(Mono.just(domain), Domain.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(repository.insert(request.bodyToMono(Domain.class)), Domain.class);
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest){

        return repository.findById(serverRequest.pathVariable("id"))
                .flatMap(user -> ServerResponse.ok()
                        .body(repository.save(serverRequest.bodyToMono(Domain.class).block()), Domain.class))
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
