package io.codebrews.wiremockdemo

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

interface WeatherService {
    fun getCurrentWeather(request: ServerRequest): Mono<ServerResponse>
}
