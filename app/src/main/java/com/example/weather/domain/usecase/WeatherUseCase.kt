package com.example.weather.domain.usecase

import com.example.weather.data.remote.weatherdto.WeatherDto
import com.example.weather.domain.repository.WeatherRepository

class WeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): WeatherDto{

        return repository.getWeather(city)
    }
}