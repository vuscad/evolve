package com.objective.evolve.repository;

import com.objective.evolve.model.Area;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AreaRepository extends ReactiveMongoRepository<Area, String> {

}
