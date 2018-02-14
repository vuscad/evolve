package com.objective.evolve;

import com.objective.evolve.handler.DomainHandler;
import com.objective.evolve.handler.PathHandler;
import com.objective.evolve.handler.TaskHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Routes {

    private final DomainHandler domainHandler;
    private final TaskHandler taskHandler;
    private final PathHandler pathHandler;

    @Autowired
    public Routes(DomainHandler domainHandler, TaskHandler taskHandler, PathHandler pathHandler) {
        this.domainHandler = domainHandler;
        this.taskHandler = taskHandler;
        this.pathHandler = pathHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routingFunction() {
        return nest(accept(MediaType.APPLICATION_JSON),
             route(GET("/domain/{id}"), domainHandler::findById)
            .andRoute(GET("/domains"), domainHandler::findAll)
            .andRoute(PUT("/domain/{id}"), domainHandler::update)
            .andRoute(DELETE("/domain/{id}"), domainHandler::delete)
            .andRoute(POST("/domain"), domainHandler::save)
            .andRoute(GET("/path/{id}"), pathHandler::findById)
            .andRoute(GET("/paths"), pathHandler::findAll)
            .andRoute(PUT("/path/{id}"), pathHandler::update)
            .andRoute(DELETE("/path/{id}"), pathHandler::delete)
            .andRoute(POST("/path"), pathHandler::save)
            .andRoute(GET("/task/{id}"), taskHandler::findById)
            .andRoute(GET("/tasks"), taskHandler::findAll)
            .andRoute(PUT("/task/{id}"), taskHandler::update)
            .andRoute(DELETE("/task/{id}"), taskHandler::delete)
            .andRoute(POST("/task"), taskHandler::save));
    }
}
