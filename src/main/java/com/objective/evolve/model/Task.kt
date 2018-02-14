package com.objective.evolve.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotNull

@Document(collection = "task")
@TypeAlias("task")
data class Task constructor(@Id var id: String?, var name: String?, var assignments: List<String>?) {
    private constructor() : this(null, null, null)
}