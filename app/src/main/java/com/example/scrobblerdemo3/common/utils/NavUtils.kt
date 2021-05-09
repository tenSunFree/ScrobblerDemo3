package com.example.scrobblerdemo3

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.composable
import com.example.scrobblerdemo3.common.utils.Screen

fun NavBackStackEntry.route() = arguments?.getString(KEY_ROUTE)

fun NavGraphBuilder.destination(
    screen: Screen, content: @Composable (List<String>) -> Unit
) {
    composable(
        route = screen.argRoute,
        arguments = screen.navArgs,
    ) { entry ->
        val args = screen.args.mapNotNull { entry.arguments?.getString(it.name) }
        content(args)
    }
}