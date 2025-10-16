package com.example.weather.domain.repository

import com.example.weather.data.remote.weatherdto.WeatherDto

interface WeatherRepository{
    suspend fun getWeather(city : String) : WeatherDto
}