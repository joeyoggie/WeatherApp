package com.musalasoft.weatherapp.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.musalasoft.weatherapp.R
import com.musalasoft.weatherapp.ui.theme.PrimaryButtonColor

@Composable
fun ShowDialog(
    title: String?,
    message: String?,
    positiveActionText: String?,
    negativeActionText: String?,
    positiveAction: (() -> Unit)?,
    negativeAction: (() -> Unit)?
) {
    AlertDialog(
        onDismissRequest = negativeAction ?: {},
        confirmButton = {
            TextButton(
                onClick = positiveAction ?: {
                },
                colors = ButtonDefaults.textButtonColors(contentColor = PrimaryButtonColor)
            ) {
                Text(text = positiveActionText ?: stringResource(R.string.ok))
            }
        },
        dismissButton = {
            if (!negativeActionText.isNullOrEmpty()) {
                TextButton(
                    onClick = negativeAction ?: {
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = PrimaryButtonColor)
                ) {
                    Text(text = negativeActionText ?: stringResource(R.string.cancel))
                }
            }
        },
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dp(16f)),
                color = Color.Black,
                textAlign = TextAlign.Center,
                text = title ?: stringResource(id = R.string.app_name)
            )
        },
        text = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dp(16f)),
                style = TextStyle(
                    color = Color.Black,
                    textAlign = TextAlign.Center
                ),
                text = message ?: ""
            )
        }
    )
}
