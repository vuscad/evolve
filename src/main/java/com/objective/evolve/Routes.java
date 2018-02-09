package com.objective.evolve;

import com.objective.evolve.handler.AreaHandler;
import com.objective.evolve.handler.PathHandler;
import com.objective.evolve.handler.SubAreaHandler;
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

    private final AreaHandler areaHandler;
    private final TaskHandler taskHandler;
    private final SubAreaHandler subAreaHandler;
    private final PathHandler pathHandler;

    @Autowired
    public Routes(AreaHandler areaHandler, TaskHandler taskHandler, SubAreaHandler subAreaHandler, PathHandler pathHandler) {
        this.areaHandler = areaHandler;
        this.taskHandler = taskHandler;
        this.subAreaHandler = subAreaHandler;
        this.pathHandler = pathHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routingFunction() {
        return nest(accept(MediaType.APPLICATION_JSON),
                         route(GET("/area/{id}"), areaHandler::findById)
                        .andRoute(GET("/areas"), areaHandler::findAll)
                        .andRoute(PUT("/area/{id}"), areaHandler::update)
                        .andRoute(DELETE("/area/{id}"), areaHandler::delete)
                        .andRoute(POST("/area"), areaHandler::save))
                .andNest(accept(MediaType.APPLICATION_JSON),
                         route(GET("/path/{id}"), pathHandler::findById)
                        .andRoute(GET("/paths"), pathHandler::findAll)
                        .andRoute(PUT("/path/{id}"), pathHandler::update)
                        .andRoute(DELETE("/path/{id}"), pathHandler::delete)
                        .andRoute(POST("/path"), pathHandler::save))
                .andNest(accept(MediaType.APPLICATION_JSON),
                        route(GET("/sub-area/{id}"), subAreaHandler::findById)
                        .andRoute(GET("/sub-areas"), subAreaHandler::findAll)
                        .andRoute(PUT("/sub-area/{id}"), subAreaHandler::update)
                        .andRoute(DELETE("/sub-area/{id}"), subAreaHandler::delete)
                        .andRoute(POST("/sub-area"), subAreaHandler::save))
                .andNest(accept(MediaType.APPLICATION_JSON),
                        route(GET("/task/{id}"), taskHandler::findById)
                        .andRoute(GET("/tasks"), taskHandler::findAll)
                        .andRoute(PUT("/task/{id}"), taskHandler::update)
                        .andRoute(DELETE("/task/{id}"), taskHandler::delete)
                        .andRoute(POST("/task"), taskHandler::save));
    }
}
