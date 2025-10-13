package com.example.weather.domain.repository

import com.example.weather.data.remote.forecastdto.ForecastDto

interface ForecastRepository {

    suspend fun getForecast(city: String): ForecastDto
}