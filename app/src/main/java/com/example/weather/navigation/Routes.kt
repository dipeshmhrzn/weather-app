package com.example.weather.navigation

import kotlinx.serialization.Serializable
import java.util.Locale

@Serializable
sealed class Routes {

    @Serializable
    data object SplashScreen : Routes()

    @Serializable
    data object WeatherHome : Routes()

    @Serializable
    data class ForecastWeather(
        val city: String,
        val countryCode: String,
    ) : Routes()

}