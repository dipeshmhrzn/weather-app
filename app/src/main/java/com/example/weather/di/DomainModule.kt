package com.example.weather.di

import com.example.weather.domain.repository.ForecastRepository
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.domain.usecase.ForecastUseCase
import com.example.weather.domain.usecase.WeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    @ViewModelScoped
    fun provideWeatherUseCase(repository: WeatherRepository): WeatherUseCase{
        return WeatherUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideForecastUseCase(repository: ForecastRepository): ForecastUseCase{
        return ForecastUseCase(repository)
    }
}