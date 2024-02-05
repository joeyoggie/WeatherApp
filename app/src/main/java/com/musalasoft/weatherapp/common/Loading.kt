package com.musalasoft.weatherapp.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.musalasoft.weatherapp.ui.theme.PrimaryPrimary

@Composable
fun ShowLoadingIndicator() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(
            trackColor = PrimaryPrimary,
            color = PrimaryPrimary,
            strokeCap = StrokeCap.Round,
            strokeWidth = 3.dp
        )
    }
}
