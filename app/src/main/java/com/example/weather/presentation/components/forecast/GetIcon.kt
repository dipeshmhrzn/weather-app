package com.example.weather.presentation.components.forecast

import com.example.weather.R

fun getIconResource(iconCode: String): Int {
    return when (iconCode) {
        "01d", "01n" -> R.drawable._01d
        "02d", "02n" -> R.drawable._02d
        "03d", "03n" -> R.drawable._03d
        "04d", "04n" -> R.drawable._04d
        "09d", "09n" -> R.drawable._09d
        "10d", "10n" -> R.drawable._10d
        "11d", "11n" -> R.drawable._11d
        "13d", "13n" -> R.drawable._13d
        "50d", "50n" -> R.drawable._50d
        else -> R.drawable._01d
    }
}