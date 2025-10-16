package com.example.weather.presentation.forecast

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.remote.forecastdto.ForecastDto
import com.example.weather.domain.usecase.ForecastUseCase
import com.example.weather.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val forecastUseCase: ForecastUseCase
) : ViewModel() {

    var forecastState by mutableStateOf<Result<ForecastDto>>(Result.Initial)
        private set

    fun fetchForecast(city: String) {

        viewModelScope.launch(Dispatchers.IO) {

            forecastState = Result.Loading

            try {
                val forecast = forecastUseCase(city)
                forecastState= Result.Success(forecast)
                Log.d("ForecastViewModel", "Weather fetched successfully: $forecast")
            }catch (e: Exception){
                forecastState = Result.Error(e.localizedMessage ?: "An unexpected error occurred")
            }
        }


    }

}