package io.codebrews.wiremockdemo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
@Suppress("unused")
class Route(private val weatherService: WeatherService) {

    @Bean
    fun buildRoute() = router {
        ("/api" and accept(APPLICATION_JSON)).nest {
            POST("/current-weather", weatherService::getCurrentWeather)
        }
    }
}
