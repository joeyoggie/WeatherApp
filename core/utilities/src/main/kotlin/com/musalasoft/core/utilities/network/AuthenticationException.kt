package com.musalasoft.core.utilities.network

class AuthenticationException(
    message: String?,
    positiveAction: (() -> Unit),
    negativeAction: (() -> Unit)
) : Throwable(message) {
    val positiveAction: (() -> Unit) = positiveAction
    val negativeAction: (() -> Unit) = negativeAction
}
