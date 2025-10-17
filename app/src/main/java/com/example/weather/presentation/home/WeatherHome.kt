package com.example.weather.presentation.home


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.weather.R
import com.example.weather.presentation.components.animations.AnimatedClouds
import com.example.weather.presentation.components.animations.LottieAnimation
import com.example.weather.presentation.components.weather.PrimaryStats
import com.example.weather.presentation.components.weather.SearchInput
import com.example.weather.presentation.components.weather.SecondaryStats
import com.example.weather.presentation.components.weather.TopAppBar
import com.example.weather.utils.Result

@Composable
fun WeatherHome(
    navHostController: NavHostController,
    viewModel: WeatherViewModel = hiltViewModel(),
    darkModeEnabled: Boolean,
    onToggleDarkMode: () -> Unit
) {


    val weatherState = viewModel.weatherState
    val city = viewModel.city



    LaunchedEffect(city) {
        if (city.isNotBlank()) {
            viewModel.updateCityAndSearch(city)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                headerText = if (weatherState is Result.Success && city.isNotBlank()) city
                else "Weather App",
                countryCode = if (weatherState is Result.Success) viewModel.countryCode
                else "",
                darkModeEnabled = darkModeEnabled,
                onToggleDarkMode = onToggleDarkMode
            )
        }) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            if (weatherState is Result.Initial || weatherState is Result.Error) {
                AnimatedClouds(-1.2f, -0.18f, 25000)
                AnimatedClouds(1.3f, 0.4f, 25000)
                AnimatedClouds(1.5f, -0.8f, 30000)
            }
            if (weatherState is Result.Success) {

                val size = if (weatherState.data.weather[0].main == "Clear") 180.dp else 200.dp

                LottieAnimation(
                    main = weatherState.data.weather[0].main,
                    modifier = Modifier
                        .size(size)
                        .align(Alignment.CenterEnd)
                        .offset(y = (-220).dp, x = 60.dp)
                        .alpha(.6f)
                )
                LottieAnimation(
                    main = weatherState.data.weather[0].main,
                    modifier = Modifier
                        .size(size)
                        .align(Alignment.Center)
                        .offset(x = (-130).dp)
                        .alpha(.6f)
                )

                LottieAnimation(
                    main = weatherState.data.weather[0].main,
                    modifier = Modifier
                        .size(size)
                        .align(Alignment.CenterEnd)
                        .offset(x = 60.dp, y = 150.dp)
                        .alpha(.6f)
                )
            }
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                SearchInput(onSearch = { query ->
                    viewModel.updateCityAndSearch(query)
                })

                Spacer(modifier = Modifier.height(40.dp))

                when (weatherState) {

                    is Result.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    is Result.Initial -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Image(
                                painter = painterResource(R.drawable.city),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                                modifier = Modifier.size(80.dp)
                            )
                            Text(
                                text = "Enter a city name to fetch the weather details..",
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(16.dp),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    is Result.Success -> {
                        PrimaryStats(weatherData = weatherState.data)

                        Spacer(modifier = Modifier.height(120.dp))

                        SecondaryStats(navHostController, weatherData = weatherState.data)

                    }


                    is Result.Error -> {
                        Log.d("WeatherHome", weatherState.message.toString())

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(25.dp)
                        ) {

                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Image(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error),
                                    modifier = Modifier.size(80.dp)
                                )
                                Text(
                                    text = "${weatherState.message}",
                                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                                    color = MaterialTheme.colorScheme.onErrorContainer
                                )

                            }
                        }
                    }

                }

            }

        }


    }

}