package com.example.weather.data.repositoryimpl

import com.example.weather.BuildConfig
import com.example.weather.data.remote.weatherdto.WeatherDto
import com.example.weather.data.services.WeatherApiServices
import com.example.weather.domain.repository.WeatherRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherRepositoryImpl(
    private val apiService: WeatherApiServices
): WeatherRepository {

    private val apiKey = BuildConfig.OPEN_WEATHER_API_KEY

    override suspend fun getWeather(city: String): WeatherDto {

        return try {
            apiService.client.get("/data/2.5/weather") {
                parameter("q", city)
                parameter("appid", apiKey)
                parameter("units", "metric")
            }.body()
        } catch (e: Exception) {

            e.printStackTrace()
            throw Exception("Failed to fetch data : ${e.localizedMessage}" )
        }

    }



}