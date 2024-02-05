package com.musalasoft.weatherapp.common

import android.Manifest
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.musalasoft.weatherapp.R

@Composable
fun GrantLocationPermission(
    positiveAction: (() -> Unit)?,
    negativeAction: (() -> Unit)?
) {
    CheckPermission(
        title = stringResource(id = R.string.location_permission_title),
        message = stringResource(id = R.string.location_permission_message),
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
        positiveAction = positiveAction,
        negativeAction = negativeAction
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun CheckPermission(
    title: String?,
    message: String?,
    permission: String,
    positiveAction: (() -> Unit)?,
    negativeAction: (() -> Unit)?
) {
    var askedForPermission by remember { mutableStateOf(false) }

    if (!askedForPermission) {
        val permissionState = rememberPermissionState(
            permission = permission,
            onPermissionResult = { result ->
                if (result) {
                    positiveAction?.invoke()
                } else {
                    negativeAction?.invoke()
                }
            }
        )

        when (permissionState.status) {
            // If the permission is granted, then show screen with the feature enabled
            PermissionStatus.Granted -> {
                positiveAction?.invoke()
                askedForPermission = true
            }
            is PermissionStatus.Denied -> {
                ShowDialog(
                    title = title,
                    message = message,
                    positiveActionText = "Allow",
                    negativeActionText = "Cancel",
                    positiveAction = {
                        permissionState.launchPermissionRequest()
                    },
                    negativeAction = {
                        negativeAction?.invoke()
                        askedForPermission = true
                    }
                )
            }
        }
    }
}
