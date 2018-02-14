package com.objective.evolve.handler

import com.objective.evolve.model.Task
import com.objective.evolve.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Service
class TaskHandler @Autowired constructor(private var repository: TaskRepository) {

    fun findAll(request: ServerRequest) : Mono<ServerResponse> {
        return ServerResponse
                .ok()
                .body(repository.findAll(), Task::class.java)
    }

    fun findById(request: ServerRequest): Mono<ServerResponse> {
        return repository.findById(request.pathVariable("id"))
            .flatMap { domain -> ServerResponse.ok().body(Mono.just(domain), Task::class.java) }
            .switchIfEmpty(ServerResponse.notFound().build())
    }

    fun save(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse
            .ok()
            .body(repository.insert(request.bodyToMono(Task::class.java)), Task::class.java)
    }

    fun update(serverRequest: ServerRequest): Mono<ServerResponse> {

        return repository.findById(serverRequest.pathVariable("id"))
            .flatMap { _ ->
                ServerResponse
                    .ok()
                    .body(repository.save(serverRequest.bodyToMono(Task::class.java).block()), Task::class.java)
            }
            .switchIfEmpty(ServerResponse.notFound().build())
    }

    fun delete(serverRequest: ServerRequest): Mono<ServerResponse> {
        return repository.findById(serverRequest.pathVariable("id"))
            .flatMap { user ->
                ServerResponse
                    .noContent()
                    .build(repository.delete(user))
            }
            .switchIfEmpty(ServerResponse.notFound().build())
    }
}