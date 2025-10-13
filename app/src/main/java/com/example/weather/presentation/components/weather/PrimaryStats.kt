package com.example.weather.presentation.components.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.data.remote.weatherdto.WeatherDto
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun PrimaryStats(weatherData: WeatherDto?) {

    if (weatherData == null) return

    val currIcon = when (weatherData.weather[0].icon) {
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


    val day = Instant.ofEpochSecond(weatherData.dt.toLong())
        .atZone(ZoneId.systemDefault()) 
        .dayOfWeek

//    val day = ZonedDateTime.ofInstant(
//        Instant.ofEpochSecond(weatherData.dt.toLong()),
//        ZoneOffset.UTC
//    ).dayOfWeek

    val dayString = day.getDisplayName(java.time.format.TextStyle.FULL, Locale.ENGLISH)

    val updatedOnTime = Instant.ofEpochSecond(weatherData.dt.toLong())
        .atZone(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern("HH:mm a"))

    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(currIcon),
            contentDescription = null,
            modifier = Modifier.size(70.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = "Today",
                fontSize = MaterialTheme.typography.displaySmall.fontSize
            )
            Text(
                text = dayString,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
        }
    }
    Spacer(modifier = Modifier.height(70.dp))

    Row {
        Text(
            text = weatherData.main.temp.roundToInt().toString(),
            fontSize = 70.sp,
            color = MaterialTheme.colorScheme.primary,
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.tertiary
                    )
                )
            )
        )
        Text(
            text = "°C",
            fontSize = 30.sp,
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.tertiary
                    )
                )
            )
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    Row {
        Text(text = weatherData.weather[0].main, color = MaterialTheme.colorScheme.onSurface)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = weatherData.main.temp_min.roundToInt().toString() + "°", color = MaterialTheme.colorScheme.onSurface)
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = "|", color = MaterialTheme.colorScheme.onSurface)
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = weatherData.main.temp_max.roundToInt().toString() + "°", color = MaterialTheme.colorScheme.onSurface)
    }
    Spacer(modifier = Modifier.height(16.dp))
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.updated),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "Updated on ", color = MaterialTheme.colorScheme.onSurface)
        Text(text = updatedOnTime, color = MaterialTheme.colorScheme.onSurface)
    }

}