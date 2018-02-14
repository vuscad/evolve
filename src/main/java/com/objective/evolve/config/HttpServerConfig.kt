package com.objective.evolve.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.ipc.netty.http.server.HttpServer


@Configuration
@EnableAutoConfiguration
@EnableReactiveMongoRepositories(basePackages = ["com.objective.evolve"])
open class HttpServerConfig @Autowired constructor(val environment: Environment) {

    @Bean open fun httpServer(routerFunction : RouterFunction<ServerResponse>): HttpServer {
        val httpHandler = RouterFunctions.toHttpHandler(routerFunction)
        val adapter = ReactorHttpHandlerAdapter(httpHandler)
        val server = HttpServer.create()
        server.newHandler(adapter)
        return server
    }
}