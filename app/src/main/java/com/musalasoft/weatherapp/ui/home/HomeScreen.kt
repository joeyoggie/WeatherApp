package com.musalasoft.weatherapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.musalasoft.weather.presentation.WeatherViewModel
import com.musalasoft.weather.presentation.data.UiWeatherItem
import com.musalasoft.weather.presentation.data.WeatherEvent
import com.musalasoft.weather.presentation.data.WeatherState
import com.musalasoft.weatherapp.R
import com.musalasoft.weatherapp.common.GrantLocationPermission
import com.musalasoft.weatherapp.common.ShowLoadingIndicator
import com.musalasoft.weatherapp.ui.theme.PrimaryTextColor
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel


@Destination(start = true)
@Composable
fun HomeScreen(
    weatherViewModel: WeatherViewModel = koinViewModel()
) {
    val viewState = weatherViewModel.weatherState.collectAsState().value

    GrantLocationPermission(
        positiveAction = {
            weatherViewModel.onEvent(WeatherEvent.GetCurrentUserLocation)
        },
        negativeAction = {
            weatherViewModel.onEvent(WeatherEvent.UserDismissResolvableException)
        }
    )

    HomeScreen(
        viewState = viewState,
        handleEvent = weatherViewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewState: WeatherState,
    handleEvent: (WeatherEvent) -> Unit
) {
    var searchText by remember {
        mutableStateOf("")
    }
    var searchBarActive by remember {
        mutableStateOf(true)
    }
    Column (
        modifier = Modifier.background(Color.White)
    ) {
        TopAppBar {

        }
        SearchBar(
            modifier = Modifier
                .fillMaxWidth(),
            query = searchText,
            onQueryChange = {
                searchText = it
                handleEvent(WeatherEvent.SearchForCity(it))
            },
            onSearch = {
                searchText = it
                handleEvent(WeatherEvent.SearchForCity(it))
            },
            active = viewState.showCities && searchBarActive,
            onActiveChange = {
                searchBarActive = it
            },
            placeholder = {
                Text(text = stringResource(id = R.string.search))
            },
            leadingIcon = {
                Icon(
                    Icons.Default.ArrowBack,
                    "ArrowBack",
                    Modifier.clickable {
                        searchBarActive = searchBarActive.not()
                    }
                )
            },
            trailingIcon = {
                if (searchText.isBlank()) {
                    Icon(
                        Icons.Default.Search,
                        "Search",
                        Modifier.clickable {
                            handleEvent(WeatherEvent.SearchForCity(searchText))
                        }
                    )
                } else {
                    Icon(
                        Icons.Default.Clear,
                        "Search",
                        Modifier.clickable {
                            searchText = ""
                            searchBarActive = false
                        }
                    )
                }
            },
            content = {
                LazyColumn(
                    contentPadding = PaddingValues(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(viewState.cities.orEmpty()) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp)
                                .clickable {
                                    searchText = it.name
                                    handleEvent(WeatherEvent.GetWeatherForCity(it))
                                },
                            fontWeight = Normal,
                            fontSize = 12.sp,
                            color = PrimaryTextColor,
                            text = it.name.plus(", ").plus(it.country)
                        )
                    }
                }
            }
        )
        if (viewState.isLoading) {
            ShowLoadingIndicator()
        } else {
            viewState.data?.let {
                Spacer(modifier = Modifier.height(12.dp))
                WeatherView(
                    cityName = viewState.city.orEmpty(),
                    data = it
                )
            }
        }
    }

//    if (viewState.error != null) {
//        ShowError(
//            error = viewState.error?.errorMessage,
//            positiveAction = viewState.error?.positiveAction,
//            negativeAction = viewState.error?.negativeAction
//        )
//    }
}

@Composable
fun WeatherView(
    cityName: String,
    data: UiWeatherItem
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(Icons.Default.LocationOn, "menu")
            Text(
                text = stringResource(id = R.string.showing_results_for),
                fontWeight = Normal,
                fontSize = 16.sp,
                color = PrimaryTextColor
            )
            Text(
                text = cityName,
                fontWeight = Bold,
                fontSize = 16.sp,
                color = PrimaryTextColor
            )
        }
        Column (
            horizontalAlignment = CenterHorizontally
        ) {
            Text(
                text = data.temperature,
                fontWeight = Bold,
                fontSize = 24.sp,
                color = PrimaryTextColor
            )
            Text(
                text = data.main,
                fontWeight = Normal,
                fontSize = 18.sp,
                color = PrimaryTextColor
            )
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(id = R.string.temp_high, data.temperatureMax),
                fontWeight = Normal,
                fontSize = 16.sp,
                color = PrimaryTextColor
            )
            Text(
                text = stringResource(id = R.string.temp_low, data.temperatureMin),
                fontWeight = Normal,
                fontSize = 16.sp,
                color = PrimaryTextColor
            )
        }
        Text(
            text = data.description,
            fontWeight = Normal,
            fontSize = 14.sp,
            color = PrimaryTextColor
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(12.dp)
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.feels_like, data.feelsLike),
                    fontWeight = Normal,
                    fontSize = 14.sp,
                    color = PrimaryTextColor
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.wind_speed, data.windSpeed),
                    fontWeight = Normal,
                    fontSize = 14.sp,
                    color = PrimaryTextColor
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.humidity, data.humidity),
                    fontWeight = Normal,
                    fontSize = 14.sp,
                    color = PrimaryTextColor
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.rain, data.rain),
                    fontWeight = Normal,
                    fontSize = 14.sp,
                    color = PrimaryTextColor
                )
            }
        }
    }
}
