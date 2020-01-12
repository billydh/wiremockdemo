package io.codebrews.wiremockdemo

import com.fasterxml.jackson.annotation.JsonProperty

data class CityId(val cityId: String)

data class Temperature(val temp: Double,
                       val pressure: Int,
                       val humidity: Int,
                       val temp_min: Long,
                       val temp_max: Long)

data class CurrentWeather(@JsonProperty("main") var details: Temperature)
