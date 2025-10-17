package com.example.weather.domain.usecase

import com.example.weather.data.remote.weatherdto.WeatherDto
import com.example.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): WeatherDto{

        return repository.getWeather(city)
    }
}