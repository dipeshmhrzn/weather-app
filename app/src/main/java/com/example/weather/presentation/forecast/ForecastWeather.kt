package com.example.weather.presentation.forecast

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.presentation.components.forecast.ForecastList
import com.example.weather.utils.Result

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastWeather(
    city: String,
    country: String,
    viewModel: ForecastViewModel = hiltViewModel()
) {

    val forecastState = viewModel.forecastState


    LaunchedEffect(city) {
        viewModel.fetchForecast(city)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row {
                        Text(
                            text = city,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "  $country",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },

                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (forecastState) {
                is Result.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                is Result.Success -> {

                    val dailyForecast = forecastState.data.list
                        .filter { it.dt_txt.contains("12:00:00") }
                        .take(5)

                    Log.d("ForcastList", "$dailyForecast")
                    ForecastList(forecast = dailyForecast)
                }

                is Result.Error -> {
                    Text("Error: ${forecastState.message}")
                }

                else -> {

                }

            }
        }
    }

}
