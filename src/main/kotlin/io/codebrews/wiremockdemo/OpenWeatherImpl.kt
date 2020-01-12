package io.codebrews.wiremockdemo

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.util.UriBuilder
import reactor.core.publisher.Mono

@Component
@Suppress("unused")
class OpenWeatherImpl(private val client: WebClient, private val config: OpenWeatherConfig): WeatherService {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun getCurrentWeather(request: ServerRequest): Mono<ServerResponse> {
        val response = request
            .bodyToMono(CityId::class.java)
            .flatMap { cityId: CityId ->
                client
                    .get()
                    .uri { builder: UriBuilder ->
                        builder.path("/weather")
                            .queryParam("id", cityId.cityId)
                            .queryParam("APPID", config.apiKey)
                            .build()
                    }
                    .retrieve()
                    .bodyToMono(CurrentWeather::class.java)
            }

        return ServerResponse
            .ok()
            .body(response)
    }
}
