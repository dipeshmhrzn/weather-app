package com.example.weather.domain.usecase

import com.example.weather.data.remote.forecastdto.ForecastDto
import com.example.weather.domain.repository.ForecastRepository
import javax.inject.Inject

class ForecastUseCase @Inject constructor(
    private val repository: ForecastRepository
) {
    suspend operator fun invoke(city: String): ForecastDto{
        return repository.getForecast(city)
    }
}