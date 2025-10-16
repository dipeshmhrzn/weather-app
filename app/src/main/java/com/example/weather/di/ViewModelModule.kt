package com.example.weather.di

import com.example.weather.domain.usecase.ForecastUseCase
import com.example.weather.domain.usecase.WeatherUseCase
import com.example.weather.presentation.forecast.ForecastViewModel
import com.example.weather.presentation.home.WeatherViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideWeatherViewModel(weatherUseCase: WeatherUseCase): WeatherViewModel{
        return WeatherViewModel(weatherUseCase)
    }

    fun provideForecastViewModel(forecastUseCase: ForecastUseCase): ForecastViewModel{
        return ForecastViewModel(forecastUseCase)
    }
}