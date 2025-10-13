package com.example.weather.presentation.components.animations

import com.example.weather.R

fun getWeatherLottie(main: String): Int {
    return when(main.lowercase()){
            "clear" -> R.raw.clear_sky
            "clouds" -> R.raw.cloudy
            "rain" -> R.raw.rainy
            "snow" -> R.raw.snowy
            "thunderstorm" -> R.raw.thunder
            "drizzle" -> R.raw.drizzle
            else -> R.raw.clear_sky
    }
}