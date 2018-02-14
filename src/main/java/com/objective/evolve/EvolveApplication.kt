package com.objective.evolve

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class EvolveApplication

fun main(args: Array<String>) {
    SpringApplication.run(EvolveApplication::class.java, *args)
}