package com.objective.evolve.repository;

import com.objective.evolve.model.Domain;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DomainRepository extends ReactiveMongoRepository<Domain, String> {

}
