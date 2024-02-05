package com.musalasoft.weatherapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.musalasoft.weatherapp.ui.home.NavGraphs
import com.musalasoft.weatherapp.ui.home.appCurrentDestinationAsState
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentDestination by navController.appCurrentDestinationAsState()

    Box {
        DestinationsNavHost(
            navController = navController,
            navGraph = NavGraphs.root
        )
    }
}
