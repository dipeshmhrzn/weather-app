package com.example.weather.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.remote.weatherdto.WeatherDto
import com.example.weather.domain.usecase.WeatherUseCase
import com.example.weather.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherUseCase: WeatherUseCase
) : ViewModel() {

    var city by mutableStateOf("")
        private set

    var countryCode by mutableStateOf("")
        private set

    var weatherState by mutableStateOf<Result<WeatherDto>>(Result.Initial)
        private set

    fun updateCityAndSearch(newCity: String) {
        city = newCity
        fetchWeather()
    }

    fun fetchWeather() {
        if (city.isBlank()) {
            Log.d("WeatherViewModel", "City is blank! Error state triggered")
            weatherState = Result.Initial
            weatherState = Result.Error("City cannot be empty")
            return
        }
        viewModelScope.launch {
            weatherState = Result.Loading
            try {
                val weather = weatherUseCase(city)
                weatherState = Result.Success(weather)
                countryCode = weather.sys.country
                Log.d("WeatherViewModel", "Weather fetched successfully: $weather")
            } catch (e: Exception) {
                weatherState = Result.Error(e.localizedMessage ?: "An unexpected error occurred")
            }
        }

    }

    fun resetWeatherState():Unit {
        weatherState = Result.Initial
    }
}