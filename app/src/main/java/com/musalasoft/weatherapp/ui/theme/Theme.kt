package com.musalasoft.weatherapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun MusalasoftTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        CompositionLocalProvider(
            content = content
        )
    }
}
