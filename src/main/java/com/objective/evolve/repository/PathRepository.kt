package com.objective.evolve.repository

import com.objective.evolve.model.Path
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface PathRepository : ReactiveMongoRepository<Path, String>