package com.orgs.orgs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class OrgsApplication

fun main(args: Array<String>) {
	runApplication<OrgsApplication>(*args)
}
