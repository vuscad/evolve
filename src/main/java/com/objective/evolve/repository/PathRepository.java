package com.objective.evolve.repository;

import com.objective.evolve.model.Path;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PathRepository extends ReactiveMongoRepository<Path, String> {
}
