package com.example.weather.data.remote.forecastdto

import kotlinx.serialization.Serializable

@Serializable
data class Clouds(
    val all: Int
)