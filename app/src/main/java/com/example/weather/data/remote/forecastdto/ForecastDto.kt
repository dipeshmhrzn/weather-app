package com.example.weather.data.remote.forecastdto

import kotlinx.serialization.Serializable

@Serializable
data class ForecastDto(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Item>,
    val message: Int
)