package com.example.scrobblerdemo3.common.utils

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Album
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument
import com.example.scrobblerdemo3.R

data class NavArgument(
    val name: String,
) {
    val navArg = navArgument(name) { nullable = true }
}

sealed class Screen(
    val routeId: String,
    val args: List<NavArgument> = emptyList(),
    @StringRes val titleId: Int,
    val icon: ImageVector,

    val argRoute: String = generateRouteWithArgPlaceholders(routeId, args),
    val navArgs: List<NamedNavArgument> = args.map { it.navArg },
) {
    object Home : Screen(routeId = "home", titleId = R.string.nav_charts, icon = Rounded.BarChart)
    object History :
        Screen(routeId = "history", titleId = R.string.nav_history, icon = Rounded.History)

    object Search : Screen(routeId = "search", titleId = R.string.nav_search, icon = Rounded.Search)
    object Profile :
        Screen(routeId = "profile", titleId = R.string.nav_profile, icon = Outlined.AccountCircle)

    object Settings :
        Screen(routeId = "settings", titleId = R.string.nav_settings, icon = Outlined.Settings)

    object ArtistDetails :
        Screen(
            routeId = "artist",
            args = listOf(NavArgument("artistName")),
            titleId = R.string.nav_history,
            icon = Outlined.AccountCircle
        )

    object AlbumDetails :
        Screen(
            "album",
            args = listOf(
                NavArgument("artist"),
                NavArgument("album")
            ),
            titleId = R.string.nav_history,
            icon = Outlined.Album
        )

    object TrackDetails :
        Screen(
            "track",
            args = listOf(
                NavArgument("artist"),
                NavArgument("track")
            ),
            titleId = R.string.nav_history,
            icon = Outlined.MusicNote
        )

    fun withArgs(args: List<String>) =
        routeId + if (args.isNotEmpty()) args.joinToString("/", "/") {
            it
        } else ""

    fun withArg(arg: String) = "$routeId/$arg"
}

fun generateRouteWithArgPlaceholders(route: String, args: List<NavArgument>) =
    route + if (args.isNotEmpty()) args.joinToString("/", "/") {
        "{${it.name}}"
    } else ""