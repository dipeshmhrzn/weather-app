package com.example.weather.data.remote.weatherdto

import kotlinx.serialization.Serializable

@Serializable
data class Clouds(
    val all: Int
)