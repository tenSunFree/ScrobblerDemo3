package com.example.scrobblerdemo3.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.scrobblerdemo3.HomeScreen
import com.example.scrobblerdemo3.common.utils.Screen
import com.example.scrobblerdemo3.common.utils.UIError
import com.example.scrobblerdemo3.destination
import com.example.scrobblerdemo3.home.viewModel.HomeViewModel
import com.example.scrobblerdemo3.home.viewModel.HomeViewModelImpl

@Composable
fun MainRouteContent(
    navController: NavHostController,
    errorHandler: @Composable (UIError) -> Unit
) {
    NavHost(navController = navController, startDestination = Screen.Home.routeId) {
        destination(Screen.Home) {
            val viewModel: HomeViewModel = hiltNavGraphViewModel<HomeViewModelImpl>()
            HomeScreen(viewModel = viewModel, errorHandler = errorHandler)
        }
    }
}