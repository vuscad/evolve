package com.objective.evolve.handler;

import com.objective.evolve.model.Task;
import com.objective.evolve.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class TaskHandler {

    private final TaskRepository repository;

    @Autowired
    public TaskHandler(TaskRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(repository.findAll(), Task.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return repository.findById(request.pathVariable("id"))
                .flatMap(task -> ServerResponse.ok().body(Mono.just(task), Task.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(repository.insert(request.bodyToMono(Task.class)), Task.class);
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest){

        return repository.findById(serverRequest.pathVariable("id"))
                .flatMap(user -> ServerResponse.ok()
                        .body(repository.save(serverRequest.bodyToMono(Task.class).block()), Task.class))
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
