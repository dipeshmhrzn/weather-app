package com.example.weather.data.services

import io.ktor.client.HttpClient
import javax.inject.Inject

class WeatherApiServices @Inject constructor(
    val client: HttpClient
)