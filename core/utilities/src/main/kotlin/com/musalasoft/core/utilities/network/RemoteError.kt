package com.musalasoft.core.utilities.network

import android.net.http.NetworkException
import com.musalasoft.core.utilities.constants.Constants
import com.musalasoft.core.utilities.toModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/*{
    "message": "Example error response",
    "errors": {
        "field_1": [
            "The field_1 field must be at least x characters."
        ]
    }
}*/
@Serializable
data class RemoteError(
    @SerialName("message")
    val message: String? = null,
    @SerialName("errors")
    val errors: JsonObject? = null
)


fun Throwable.userErrorMessage() = runCatching {
    if (message?.toModel<RemoteError>()?.message == "Unauthenticated") {
        AuthenticationException(message = message/*?.toModel<RemoteError>()?.message*/, {}, {})
    } else if (message?.contains("Unable to resolve", ignoreCase = true) == true) {
        Throwable(
            Constants.NO_NETWORK_MESSAGE
        )
    } else {
        val errorMessage = message?.toModel<RemoteError>()?.message
            ?: "An unknown error has occurred. Please try again later."
        Throwable(errorMessage)
    }
}.getOrDefault(Throwable("An unknown error has occurred. Please try again later."))
