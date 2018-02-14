package com.objective.evolve.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotNull

@Document(collection = "area")
@TypeAlias("area")
data class Domain constructor(@Id var id: String?, var name: String?, @DBRef var tasks: List<Task>?) {
    private constructor() : this(null, null, null)
}