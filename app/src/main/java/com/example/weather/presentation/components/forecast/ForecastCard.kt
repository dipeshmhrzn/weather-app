package com.example.weather.presentation.components.forecast

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R

@Composable
fun ForecastCard(
    icon: Int,
    day: String,
    temp: String,
    feelsLike: String,
    humidity: String,
    windDeg: String,
    windSpeed: String

) {

    val infiniteTransition = rememberInfiniteTransition(label = "iconPulse")

    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "iconScale"
    )




    Surface(
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(150.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.5f)
            ) {
//                Image(
//                    painter = painterResource(icon),
//                    contentDescription = null,
//                    modifier = Modifier.size(60.dp)
//                )

                Image(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale
                        )
                )

                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = day,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = temp,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                )
                Text(
                    text = "°C",
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(Alignment.Top)
                        .padding(top = 16.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.weight(.1f))
            }
            HorizontalDivider(
                modifier = Modifier.padding(5.dp),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.5f)
            ) {
                Image(
                    painter = painterResource(R.drawable.thermometer),
                    contentDescription = "real feel",
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "$feelsLike°",
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(20.dp))

                Image(
                    painter = painterResource(R.drawable.humidity),
                    contentDescription = "humidity",
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "$humidity%",
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(20.dp))

                Image(
                    painter = painterResource(R.drawable.compass),
                    contentDescription = "wind direction",
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "${windDeg}°",
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(20.dp))

                Image(
                    painter = painterResource(R.drawable.speed),
                    contentDescription = "wind speed",
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "${windSpeed}m/s",
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(20.dp))


            }

        }

    }
}


@Preview(showBackground = true)
@Composable
private fun ForecastScreenPrev() {
    ForecastCard(
        icon = R.drawable._01d,
        day = "Monday",
        temp = "25",
        feelsLike = "25",
        humidity = "25",
        windDeg = "25",
        windSpeed = "25"
    )
}