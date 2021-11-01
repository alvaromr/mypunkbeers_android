package com.alvaromr.mypunkbeers.ui.screen.detail

import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.alvaromr.mypunkbeers.ui.navigation.NavDirection
import com.alvaromr.mypunkbeers.ui.navigation.NavigationCommand
import com.alvaromr.mypunkbeers.ui.screen.Screen

object BeerDetailScreenDirection : NavDirection {
    const val KEY_ID = "id"

    override val routeConfig = buildRoute("{$KEY_ID}")

    override val screen: Screen = BeerDetailScreen

    fun destination(id: Int) = object : NavigationCommand.RoutedNavigationCommand {
        override val arguments = listOf(
            navArgument(KEY_ID) { type = NavType.IntType }
        )

        override val route = buildRoute(id.toString())
    }

    private fun buildRoute(id: String) = "BeerDetailScreen/$id"
}