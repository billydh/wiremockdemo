package io.codebrews.wiremockdemo

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@Suppress("unused")
class OpenWeatherConfig(@Value("\${app.openweather.baseurl}") val baseUrl: String) {
    val apiKey = System.getenv("OPENWEATHER_API_KEY") ?: "test"

    @Bean
    fun createWebClient(): WebClient {
        return WebClient.create(baseUrl)
    }
}
