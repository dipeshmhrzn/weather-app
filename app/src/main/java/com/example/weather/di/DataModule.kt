package com.example.weather.di

import com.example.weather.data.repositoryimpl.ForecastRepositoryImpl
import com.example.weather.data.repositoryimpl.WeatherRepositoryImpl
import com.example.weather.data.services.WeatherApiServices
import com.example.weather.domain.repository.ForecastRepository
import com.example.weather.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient{
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 15000
                connectTimeoutMillis = 15000
                socketTimeoutMillis = 15000
            }

            defaultRequest {

                url {
                    protocol= URLProtocol.HTTPS
                    host= "api.openweathermap.org"
                }
            }
        }
    }

    @Provides
    @Singleton
    fun provideWeatherApiServices(httpclient: HttpClient):WeatherApiServices{
        return WeatherApiServices(httpclient)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(apiServices: WeatherApiServices): WeatherRepository{
        return WeatherRepositoryImpl(apiServices)
    }

    @Provides
    @Singleton
    fun provideForecastRepository(apiServices: WeatherApiServices): ForecastRepository{
        return ForecastRepositoryImpl(apiServices)
    }



}