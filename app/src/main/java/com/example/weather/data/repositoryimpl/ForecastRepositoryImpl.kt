package com.example.weather.data.repositoryimpl

import com.example.weather.BuildConfig
import com.example.weather.data.remote.forecastdto.ForecastDto
import com.example.weather.data.services.WeatherApiServices
import com.example.weather.domain.repository.ForecastRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ForecastRepositoryImpl(
    private val apiService: WeatherApiServices
): ForecastRepository {
    private val apiKey = BuildConfig.OPEN_WEATHER_API_KEY



    override suspend fun getForecast(city: String): ForecastDto {
        return try {
            apiService.client.get("/data/2.5/forecast") {
                parameter("q",city)
                parameter("appid",apiKey)
                parameter("units","metric")
            }.body()
        }catch (e: Exception){
            e.printStackTrace()
            throw Exception("Failed to fetch data : ${e.localizedMessage}")
        }
    }
}