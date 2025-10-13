package com.example.weather.presentation.components.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weather.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    headerText: String,
    countryCode: String,
    darkModeEnabled: Boolean,
    onToggleDarkMode: () -> Unit
) {

    CenterAlignedTopAppBar(
        title = {
            Row {
                Text(
                    text = headerText,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "  $countryCode",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },

        actions = {
            IconButton(onClick = onToggleDarkMode) {
                Image(
                    painter = if (darkModeEnabled) painterResource(R.drawable.nightmode) else painterResource(R.drawable.lightmode),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = Modifier.padding(6.dp).size(25.dp)
                )
            }
        },


        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}