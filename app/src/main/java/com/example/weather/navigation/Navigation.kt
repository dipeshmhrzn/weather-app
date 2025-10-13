package com.example.weather.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.weather.data.repositoryimpl.ForecastRepositoryImpl
import com.example.weather.data.repositoryimpl.WeatherRepositoryImpl
import com.example.weather.data.services.WeatherApiServices
import com.example.weather.domain.usecase.ForecastUseCase
import com.example.weather.domain.usecase.WeatherUseCase
import com.example.weather.presentation.forecast.ForecastViewModel
import com.example.weather.presentation.forecast.ForecastWeather
import com.example.weather.presentation.home.WeatherHome
import com.example.weather.presentation.home.WeatherViewModel

@Composable
fun Navigation(
    darkModeEnabled: Boolean,
    onToggleDarkMode: () -> Unit
) {
    val navController = rememberNavController()

    val apiService = remember { WeatherApiServices() }

    val weatherRepository = remember { WeatherRepositoryImpl(apiService) }
    val weatherUseCase = remember { WeatherUseCase(weatherRepository) }
    val weatherViewModel = remember { WeatherViewModel(weatherUseCase) }

    val forecastRepository = remember { ForecastRepositoryImpl(apiService) }
    val forecastUseCase = remember { ForecastUseCase(forecastRepository) }
    val forecastViewModel = remember { ForecastViewModel(forecastUseCase) }



    NavHost(navController = navController, startDestination = Routes.WeatherHome) {

        composable<Routes.WeatherHome> {
            WeatherHome(
                navHostController = navController,
                viewModel = weatherViewModel,
                darkModeEnabled = darkModeEnabled,
                onToggleDarkMode = onToggleDarkMode
            )
        }

        composable<Routes.ForecastWeather> {backStackEntry->
            val forecastScreen = backStackEntry.toRoute<Routes.ForecastWeather>()
            ForecastWeather(
                city= forecastScreen.city,
                country = forecastScreen.countryCode,
                viewModel = forecastViewModel
            )
        }


    }

}