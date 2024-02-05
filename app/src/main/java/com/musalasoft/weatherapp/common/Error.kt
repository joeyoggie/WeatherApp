package com.musalasoft.weatherapp.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.musalasoft.weatherapp.R

@Composable
fun ShowError(
    error: String?,
    positiveAction: (() -> Unit)?,
    negativeAction: (() -> Unit)?
) {
    ShowDialog(
        title = stringResource(R.string.error),
        message = error ?: stringResource(R.string.general_error_message),
        positiveActionText = stringResource(R.string.ok),
        negativeActionText = null,
        positiveAction = positiveAction,
        negativeAction = negativeAction
    )
}
