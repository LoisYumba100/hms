package com.hms.hms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class HmsApplication

fun main(args: Array<String>) {
	runApplication<HmsApplication>(*args)
}
