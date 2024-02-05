package com.musalasoft.core.utilities.view

import com.musalasoft.core.utilities.error.UiError
import com.musalasoft.core.utilities.network.AuthenticationException

interface CustomViewState {
    val authException: AuthenticationException?
    val error: UiError?
    val isLoading: Boolean
    val isEmpty: Boolean
}
