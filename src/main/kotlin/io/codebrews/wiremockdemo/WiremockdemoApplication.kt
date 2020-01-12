package io.codebrews.wiremockdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WiremockdemoApplication

fun main(args: Array<String>) {
	runApplication<WiremockdemoApplication>(*args)
}
