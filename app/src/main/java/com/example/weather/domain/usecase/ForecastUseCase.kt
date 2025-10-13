package com.example.weather.domain.usecase

import com.example.weather.data.remote.forecastdto.ForecastDto
import com.example.weather.domain.repository.ForecastRepository

class ForecastUseCase(
    private val repository: ForecastRepository
) {
    suspend operator fun invoke(city: String): ForecastDto{
        return repository.getForecast(city)
    }
}