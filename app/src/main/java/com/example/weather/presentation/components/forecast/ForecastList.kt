package com.example.weather.presentation.components.forecast

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.weather.data.remote.forecastdto.Item
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun ForecastList(forecast : List<Item>) {


        LazyColumn {
        items(forecast){item->

            val dtTxt = item.dt_txt

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val dateTime = LocalDateTime.parse(dtTxt, formatter)

            val dayOfWeek = dateTime.dayOfWeek

            val dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)


            ForecastCard(
                icon = getIconResource(item.weather.firstOrNull()?.icon ?: "01d"),
                day = dayName,
                temp = item.main.temp.roundToInt().toString(),
                feelsLike = item.main.feels_like.roundToInt().toString(),
                humidity = item.main.humidity.toString(),
                windDeg = item.wind.deg.toString(),
                windSpeed = item.wind.speed.toString()
            )

        }
    }

}