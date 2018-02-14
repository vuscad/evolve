package com.objective.evolve.repository

import com.objective.evolve.model.Domain
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface DomainRepository : ReactiveMongoRepository<Domain, String>