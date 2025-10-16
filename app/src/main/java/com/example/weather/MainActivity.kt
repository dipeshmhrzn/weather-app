package com.example.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.weather.navigation.Navigation
import com.example.weather.presentation.home.WeatherHome
import com.example.weather.ui.theme.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var darkModeEnabled by remember { mutableStateOf(false) }

            WeatherTheme(darkTheme = darkModeEnabled) {
                Navigation(
                    darkModeEnabled = darkModeEnabled,
                    onToggleDarkMode = { darkModeEnabled = !darkModeEnabled }
                )
            }
        }
    }
}