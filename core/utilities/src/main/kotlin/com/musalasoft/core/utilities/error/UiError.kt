package com.musalasoft.core.utilities.error

data class UiError(
    val errorMessage: String,
    val positiveAction: (() -> Unit),
    val negativeAction: (() -> Unit)
)
