package com.objective.evolve.repository;

import com.objective.evolve.model.Task;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TaskRepository extends ReactiveMongoRepository<Task, String> {

}
