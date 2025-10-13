package com.example.weather.data.remote.forecastdto

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val sys: Sys,
    val visibility: Int?=null,
    val weather: List<Weather>,
    val wind: Wind
)