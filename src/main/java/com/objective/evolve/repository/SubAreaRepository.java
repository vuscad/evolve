package com.objective.evolve.repository;

import com.objective.evolve.model.SubArea;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SubAreaRepository extends ReactiveMongoRepository<SubArea, String> {

}
