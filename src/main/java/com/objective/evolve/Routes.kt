package com.objective.evolve

import com.objective.evolve.handler.DomainHandler
import com.objective.evolve.handler.PathHandler
import com.objective.evolve.handler.TaskHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
open class Routes @Autowired constructor(
    private var domainHandler: DomainHandler,
    private var taskHandler: TaskHandler,
    private var pathHandler: PathHandler) {

    @Bean open fun routingFunction() = router {

        (accept(MediaType.APPLICATION_JSON)).nest {

            GET("/domains", domainHandler::findAll)
            GET("/domain/{id}", domainHandler::findById)
            POST("/domain", domainHandler::save)
            PUT("/domain/{id}", domainHandler::update)
            DELETE("/domain/{id}", domainHandler::delete)

            GET("/paths", pathHandler::findAll)
            GET("/path/{id}", pathHandler::findById)
            POST("/path", pathHandler::save)
            PUT("/path/{id}", pathHandler::update)
            DELETE("/path/{id}", pathHandler::delete)

            GET("/tasks", taskHandler::findAll)
            GET("/task/{id}", taskHandler::findById)
            POST("/task", taskHandler::save)
            PUT("/task/{id}", taskHandler::update)
            DELETE("/task/{id}", taskHandler::delete)
        }
    }
}